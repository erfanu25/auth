package com.spring.sio.serviceImpl;

import com.spring.sio.dto.AddressDto;
import com.spring.sio.dto.UserDto;
import com.spring.sio.entity.UserEntity;
import com.spring.sio.repository.UserRepository;
import com.spring.sio.service.IUserService;
import com.spring.sio.util.PasswordUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordUtils passwordUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        if (userRepository.findUserByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("User Already Exist");
        }

        for (int i = 0; i < userDto.getAddressList().size(); i++) {
            AddressDto addressDto = userDto.getAddressList().get(i);
            addressDto.setUserDetails(userDto);
            addressDto.setAddressId(passwordUtils.generateAddressId(30));
            userDto.getAddressList().set(i, addressDto);
        }

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(passwordUtils.generateUserId(30));
        UserEntity persistentEntity = userRepository.save(userEntity);

        UserDto returnResponse = modelMapper.map(persistentEntity, UserDto.class);

        return returnResponse;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto getUserById(String id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new UsernameNotFoundException(id);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(id);
        if (userEntity == null) throw new UsernameNotFoundException(id);
        BeanUtils.copyProperties(userDto, userEntity);
        UserEntity updatedUser = userRepository.save(userEntity);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
