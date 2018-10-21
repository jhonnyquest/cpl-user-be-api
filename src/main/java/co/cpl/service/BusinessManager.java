/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.service;

import co.cpl.dto.UsersDto;

/***
 * Interface for business manager module
 *
 * @author jmunoz
 *
 */
public interface BusinessManager {
    // All implemented business methods should be declared here
    // example:
    // Users loadPayment(UsersDto load);
    UsersDto findUserById(String id);

    Boolean saveUser(UsersDto usersDto);

    Boolean updateUser(UsersDto usersDto);

    UsersDto login(UsersDto usersDto);
}
