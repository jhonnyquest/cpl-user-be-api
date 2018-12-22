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

import co.cpl.domain.Users;
import co.cpl.dto.UsersDto;
import co.cpl.service.BusinessManager;
import co.cpl.data.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


/***
 * Implementation for business manager module
 *
 * @author jmunoz
 *
 */
@Component
public class BussinessManagerImpl implements BusinessManager{

    //private UsersRepository usersRepository;
    private static UsersRepository usersRepository;

    public BussinessManagerImpl() {
        usersRepository = new UsersRepository();
    }

    @Override
    public UsersDto findUserById(String Id) {
        Optional<Users> users = usersRepository.findUserById(Id);
        if (!users.isPresent()) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
        //TODO: Replace this code for mapper approach
        UsersDto response = new UsersDto();
        response.setId(users.get().getId());
        response.setPhone(users.get().getPhone());
        response.setName(users.get().getName());
        response.setLast_name(users.get().getLast_name());
        response.setCity(users.get().getCity());
        response.setCountry(users.get().getCountry());
        response.setImei(users.get().getImei());
        response.setStatus(users.get().getStatus());
        response.setPassword(users.get().getPassword());
        response.setEmail(users.get().getEmail());
        response.setDocument_number(users.get().getDocument_number());
        response.setDocument_type(users.get().getDocument_type());
        return response;
    }

  @Override
  public UsersDto findUserByEmail(UsersDto usersDto) {
    Optional<Users> users = usersRepository.findUserByEmail(usersDto);
    if (!users.isPresent()) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
    //TODO: Replace this code for mapper approach

    System.out.println(users.toString());
    UsersDto response = new UsersDto();
    response.setId(users.get().getId());
    response.setPhone(users.get().getPhone());
    response.setName(users.get().getName());
    response.setLast_name(users.get().getLast_name());
    response.setCity(users.get().getCity());
    response.setCountry(users.get().getCountry());
    response.setImei(users.get().getImei());
    response.setStatus(users.get().getStatus());
    response.setPassword(users.get().getPassword());
    response.setEmail(users.get().getEmail());
    response.setDocument_number(users.get().getDocument_number());
    response.setDocument_type(users.get().getDocument_type());
    System.out.println(response.toString());
    return response;
  }

    @Override
    public List<UsersDto> findUsers(int limit, int offset) {
        List<Users> users = usersRepository.getUsers(limit, offset);
        List<UsersDto> response = new LinkedList<>();
        if(users.isEmpty()) {
            return response;
        }

        for (Users user: users) {
            UsersDto userDto = new UsersDto();
            userDto.setId(user.getId());
            userDto.setPhone(user.getPhone());
            userDto.setName(user.getName());
            userDto.setLast_name(user.getLast_name());
            userDto.setCity(user.getCity());
            userDto.setCountry(user.getCountry());
            userDto.setImei(user.getImei());
            userDto.setStatus(user.getStatus());
            userDto.setPassword(user.getPassword());
            userDto.setEmail(user.getEmail());
            userDto.setCreateDate(user.getCreatedAt());
            userDto.setUpdateDate(user.getUpdatedAt());
            userDto.setDocument_number(user.getDocument_number());
            userDto.setDocument_type(user.getDocument_type());
            response.add(userDto);
        }
        return response;
    }

    @Override
    public Integer saveUser(UsersDto usersDto) {
        return usersRepository.saveUser(usersDto);
    }

    @Override
    public Boolean updateUser(UsersDto usersDto) {
        return usersRepository.updateUser(usersDto);
    }

    @Override
    public Boolean changePass(UsersDto usersDto) { return usersRepository.changePass(usersDto); }

    @Override
    public void deleteUser(String userId) {
        Optional<Users> currentUser = usersRepository.findUserById(userId);
        if (!currentUser.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        usersRepository.deleteUser(userId);
    }

    @Override
    public UsersDto login(UsersDto usersDto) {
        Optional<Users> users = usersRepository.login(usersDto);

        //System.out.println(users.toString());
        if (!users.isPresent()) {throw new HttpClientErrorException(HttpStatus.NOT_FOUND); }
        //TODO: Replace this code for mapper approach
        UsersDto response = new UsersDto();
        response.setId(users.get().getId());
        response.setPhone(users.get().getPhone());
        response.setName(users.get().getName());
        response.setLast_name(users.get().getLast_name());
        response.setCity(users.get().getCity());
        response.setCountry(users.get().getCountry());
        response.setImei(users.get().getImei());
        response.setStatus(users.get().getStatus());
        response.setPassword(users.get().getPassword());
        response.setEmail(users.get().getEmail());
        response.setDocument_number(users.get().getDocument_number());
        response.setDocument_type(users.get().getDocument_type());
        System.out.println(response.toString());
        return response;
    }

}
