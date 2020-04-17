package com.spring.sio.service;

import com.spring.sio.dto.AddressDto;

import java.util.List;

public interface IAddressService {
    List<AddressDto> getAddressList(String id);

    AddressDto getAddress(String addressId);
}
