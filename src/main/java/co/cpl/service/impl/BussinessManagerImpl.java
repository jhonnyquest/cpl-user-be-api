/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/

package co.cpl.service.impl;

import co.cpl.domain.User;
import co.cpl.dto.UserDto;
import co.cpl.service.BusinessManager;
import co.cpl.data.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/***
 * Implementation for business manager module
 *
 * @author jmunoz
 *
 */
@Component
public class BussinessManagerImpl implements BusinessManager{

    //private UserRepository userRepository;
    private static UserRepository userRepository;

    public BussinessManagerImpl() {
        userRepository = new UserRepository();
    }

    @Override
    public UserDto findUserById(String Id) {
        Optional<User> user = userRepository.getUserById(Id);
        if (!user.isPresent()) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
        //TODO: Replace this code for mapper approach
        UserDto response = new UserDto();
        response.setId(Id);
        response.setPhone(user.get().getPhone());
        response.setName(user.get().getName());
        response.setStatus(user.get().getStatus());
        response.setCreatedAt(user.get().getCreatedAt());
        response.setUpdatedAt(user.get().getUpdatedAt());
        return response;
    }

    @Override
    public List<UserDto> findUsers(int limit, int offset) {
        List<User> users = userRepository.getUsers(limit, offset);
        List<UserDto> response = new LinkedList<>();
        if(users.isEmpty()) {
            return response;
        }

        for (User user: users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            userDto.setStatus(user.getStatus());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setUpdatedAt(user.getUpdatedAt());
            response.add(userDto);
        }
        return response;
    }

    @Override
    public String createUser(UserDto user) {
        String userId = userRepository.createUser(new User.Builder()
                .setName(user.getName())
                .setStatus(user.getStatus())
                .setPhone(user.getPhone())
                .build());
        if (Objects.isNull(userId) || userId.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_MODIFIED);
        }
        return userId;
    }

    @Override
    public void updateUser(UserDto user) {
        Optional<User> currentUser = userRepository.getUserById(user.getId());
        if (!currentUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        userRepository.updateUser(new User.Builder()
                .setId(user.getId())
                .setName((Objects.isNull(user.getName()) || user.getName().isEmpty()) ? currentUser.get().getName() : user.getName())
                .setPhone((Objects.isNull(user.getPhone()) || user.getPhone().isEmpty()) ? currentUser.get().getPhone() : user.getPhone())
                .setStatus((Objects.isNull(user.getStatus()) || user.getStatus().isEmpty()) ? currentUser.get().getStatus() : user.getStatus())
                .build());
    }

    @Override
    public void deleteUser(String userId) {
        Optional<User> currentUser = userRepository.getUserById(userId);
        if (!currentUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteUser(userId);
    }
}
