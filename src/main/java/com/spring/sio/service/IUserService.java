package com.spring.sio.service;

import com.spring.sio.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    UserDto getUserById(String id);

}
