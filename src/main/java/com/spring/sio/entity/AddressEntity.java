package com.spring.sio.entity;

import com.spring.sio.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "address")
@Entity
@Setter
@Getter
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 2424234234234L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30, nullable = false)
    private String addressId;

    @Column(length = 30, nullable = false)
    private String city;

    @Column(length = 15, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 7, nullable = false)
    private String postalCode;

    @Column(length = 10, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;

}
