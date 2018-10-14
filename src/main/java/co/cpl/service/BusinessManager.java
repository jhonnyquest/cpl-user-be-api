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

import co.cpl.domain.User;
import co.cpl.dto.UserDto;

import java.util.List;

/***
 * Interface for business manager module
 *
 * @author jmunoz
 *
 */
public interface BusinessManager {
    // All implemented business methods should be declared here
    // example:
    // User loadPayment(UserDto load);
    UserDto findUserById(String id);

    List<UserDto> findUsers(int limit, int offset);

    String createUser(UserDto user);

    void updateUser(UserDto user);

    void deleteUser(String userId);
}
