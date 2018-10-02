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

import co.cpl.domain.Users;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

import static javax.swing.text.html.HTML.Tag.I;

@Component
public class UsersRepository {

    private final DataSource ds;

    public UsersRepository() {

        this.ds = DataSourceSingleton.getInstance();
    }

    public Optional<Users> getUsers(String Id) {
        QueryRunner run = new QueryRunner(ds);
        try {
            String query = "SELECT * FROM cpl_users.users WHERE id = '" + I + "';";
            Optional<Users> users = run.query(query,
                    rs -> {
                        if (!rs.next()) {
                            Optional<Object> empty = Optional.empty();
                            return Optional.empty();
                        }
                        rs.last();
                        return Optional.ofNullable(new Users.Builder()
                                .setId(rs.getString(1))
                                .setName(rs.getString(2))
                                .setStatus(rs.getString(3))
                                .setPhone(rs.getString(4))
                                .setCreatedAt(rs.getString(5))
                                .setUpdatedAt(rs.getString(6))
                                .build());
                    });
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
