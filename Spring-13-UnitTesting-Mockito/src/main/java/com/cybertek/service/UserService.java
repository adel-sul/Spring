package com.cybertek.service;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.exception.TicketingProjectException;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    UserDTO findByUserName(String name);

    void save(UserDTO user);

    UserDTO update(UserDTO user);

    void deleteByUserName(String userName);

    void delete(String userName) throws TicketingProjectException;

    List<UserDTO> findByRole(String role);

    boolean checkIfUserCanBeDeleted(User user);
}
