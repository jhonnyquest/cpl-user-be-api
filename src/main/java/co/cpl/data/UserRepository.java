/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.data;

import co.cpl.domain.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepository {

    private final DataSource ds;

    public UserRepository() {

        this.ds = DataSourceSingleton.getInstance();
    }

    public Optional<User> getUserById(String id) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users WHERE id = '" + id + "';";
            return run.query(query,
                    rs -> {
                        if (!rs.next()) {
                            return Optional.empty();
                        }
                        rs.last();
                        return Optional.ofNullable(new User.Builder()
                                .setId(rs.getString(1))
                                .setName(rs.getString(2))
                                .setStatus(rs.getString(3))
                                .setPhone(rs.getString(4))
                                .setCreatedAt(rs.getString(5))
                                .setUpdatedAt(rs.getString(6))
                                .build());
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getUsers(int limit, int offset) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users LIMIT " + limit + " OFFSET " + offset + ";";
            return run.query(query,
                    rs -> {
                        List<User> newClientsList = new LinkedList<>();
                        while (rs.next()){
                            newClientsList.add(new User.Builder()
                                    .setId(rs.getString(1))
                                    .setName(rs.getString(2))
                                    .setStatus(rs.getString(3))
                                    .setPhone(rs.getString(4))
                                    .setCreatedAt(rs.getString(5))
                                    .setUpdatedAt(rs.getString(6))
                                    .build()
                            );
                        }
                        return newClientsList;
                    });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String createUser(User client) {
        QueryRunner run = new QueryRunner(ds);

        String userId = UUID.randomUUID().toString();
        try {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(false);
            try {
                String insert = "INSERT INTO cpl_users.users " +
                        "(id, name, status, phone) " +
                        "VALUES " +
                        "('" + userId + "', " +
                        "'" + client.getName() + "', " +
                        "'" + client.getStatus() + "', " +
                        "'" + client.getPhone() + "');";
                run.insert(conn, insert, new ScalarHandler<>());
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            } finally {
                DbUtils.close(conn);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userId;
    }

    public void updateUser(User user) {
        try {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            try {
                String update = "UPDATE cpl_users.users " +
                        "SET name = '" + user.getName() + "', "+
                        "status = '" + user.getStatus() + "', "+
                        "phone = '" + user.getPhone() + "' "+
                        "WHERE " +
                        "id = '" + user.getId() + "';";
                stmt.executeUpdate(update);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            } finally {
                DbUtils.close(conn);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String userId) {
        try {
            Connection conn = ds.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            try {
                String delete = "DELETE FROM cpl_users.users " +
                        "WHERE " +
                        "id = '" + userId + "';";
                stmt.execute(delete);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            } finally {
                DbUtils.close(conn);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
