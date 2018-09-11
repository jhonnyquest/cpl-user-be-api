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

import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UsersRepository {

    private final DataSource ds;

    public UsersRepository() {

        this.ds = DataSourceSingleton.getInstance();
    }
}
