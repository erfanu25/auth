package com.spring.sio.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestModel {

    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode ;
    private String type;
}
