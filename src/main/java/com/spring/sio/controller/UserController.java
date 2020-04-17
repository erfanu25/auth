package com.spring.sio.controller;

import com.spring.sio.dto.AddressDto;
import com.spring.sio.dto.UserDto;
import com.spring.sio.model.request.UserDetailsRequestModel;
import com.spring.sio.model.response.AddressResponseModel;
import com.spring.sio.model.response.UserDetailsResponseModel;
import com.spring.sio.repository.UserRepository;
import com.spring.sio.service.IAddressService;
import com.spring.sio.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    IAddressService addressService;

    @GetMapping(path = "/{id}")
    public UserDetailsResponseModel get(@PathVariable String id) {
        UserDetailsResponseModel userDetailsResponse = new UserDetailsResponseModel();

        UserDto userDto = userService.getUserById(id);

        BeanUtils.copyProperties(userDto, userDetailsResponse);

        return userDetailsResponse;
    }

    @PostMapping
    public UserDetailsResponseModel save(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {


        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetailsRequestModel, UserDto.class);
        UserDto createUser = userService.createUser(userDto);

        UserDetailsResponseModel userResponse = modelMapper.map(createUser, UserDetailsResponseModel.class);

        return userResponse;
    }

    @PutMapping(path = "/{id}")
    public UserDetailsResponseModel update(@PathVariable String id, @RequestBody UserDetailsResponseModel userDetailsResponseModel) {
        UserDetailsResponseModel userDetailsResponse = new UserDetailsResponseModel();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetailsResponseModel, userDto);
        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, userDetailsResponse);

        return userDetailsResponse;
    }

    @GetMapping(path = "/{id}/addresses")
    public List<AddressResponseModel> getUserAddressList(@PathVariable String id) {

        List<AddressResponseModel> addressResponseModels = new ArrayList<>();

        List<AddressDto> addressDtos = addressService.getAddressList(id);

        if (addressDtos != null || addressDtos.isEmpty()) {
            Type listType = new TypeToken<List<AddressResponseModel>>() {
            }.getType();

            addressResponseModels = new ModelMapper().map(addressDtos, listType);
        }

        return addressResponseModels;
    }

    @GetMapping(path = "/{userId}/addresses/{addressId}")
    public AddressResponseModel getAddress(@PathVariable String addressId) {
        AddressDto addressDto =addressService.getAddress(addressId);
        ModelMapper modelMapper =new ModelMapper();

        return modelMapper.map(addressDto, AddressResponseModel.class);
    }

    @DeleteMapping
    public String delete() {
        return "Delete Users are called";
    }

}
