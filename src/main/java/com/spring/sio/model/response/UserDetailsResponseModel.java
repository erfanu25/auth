package com.spring.sio.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDetailsResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressResponseModel> addressList;
}
