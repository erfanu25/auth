package com.spring.sio.serviceImpl;

import com.spring.sio.dto.AddressDto;
import com.spring.sio.entity.AddressEntity;
import com.spring.sio.entity.UserEntity;
import com.spring.sio.repository.AddressRepository;
import com.spring.sio.repository.UserRepository;
import com.spring.sio.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddressList(String id) {
        List<AddressDto> addressList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null) return addressList;

        Iterable<AddressEntity> addressEntities = addressRepository.findAllByUserDetails(userEntity);

        for (AddressEntity addressEntity : addressEntities) {
            addressList.add(modelMapper.map(addressEntity, AddressDto.class));
        }

        return addressList;
    }

    @Override
    public AddressDto getAddress(String addressId) {
        AddressDto addressDto = null;
        AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
        if (addressEntity != null) {
            addressDto = new ModelMapper().map(addressEntity, AddressDto.class);
        }
        return addressDto;
    }
}
