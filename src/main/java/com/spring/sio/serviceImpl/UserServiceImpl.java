package com.spring.sio.serviceImpl;

import com.spring.sio.dto.UserDto;
import com.spring.sio.entity.UserEntity;
import com.spring.sio.repository.UserRepository;
import com.spring.sio.service.IUserService;
import com.spring.sio.util.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword() ));
        userEntity.setUserId(passwordUtils.generateUserId(30));
        UserEntity persistentEntity = userRepository.save(userEntity);

        UserDto returnResponse = new UserDto();
        BeanUtils.copyProperties(persistentEntity, returnResponse);

        return returnResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
