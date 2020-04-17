package com.spring.sio.repository;

import com.spring.sio.entity.AddressEntity;
import com.spring.sio.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity ,Long> {

    Iterable<AddressEntity> findAllByUserDetails(UserEntity userEntity);

    AddressEntity findByAddressId(String addressId);
}
