package com.spring.sio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 2424234234234L;
    private long id ;
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode ;
    private String type;
    private UserDto userDetails;

}
