package com.spring.sio.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsResponseModel {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
