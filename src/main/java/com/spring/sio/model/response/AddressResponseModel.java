package com.spring.sio.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseModel {

    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode ;
    private String type;
}
