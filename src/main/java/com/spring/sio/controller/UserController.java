package com.spring.sio.controller;

import com.spring.sio.dto.UserDto;
import com.spring.sio.entity.UserEntity;
import com.spring.sio.model.request.UserDetailsRequestModel;
import com.spring.sio.model.response.UserDetailsResponseModel;
import com.spring.sio.repository.UserRepository;
import com.spring.sio.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/{id}")
    public UserDetailsResponseModel get(@PathVariable String id) {
        UserDetailsResponseModel userDetailsResponse = new UserDetailsResponseModel();

        UserDto userDto = userService.getUserById(id);
        BeanUtils.copyProperties(userDto, userDetailsResponse);

        return userDetailsResponse;
    }

    @PostMapping
    public UserDetailsResponseModel save(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {

        UserDetailsResponseModel userResponse = new UserDetailsResponseModel();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetailsRequestModel, userDto);
        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, userResponse);

        return userResponse;
    }

    @PutMapping
    public String update() {
        return "Update Users are called";
    }

    @DeleteMapping
    public String delete() {
        return "Delete Users are called";
    }

}
