package com.rpdevelopment.user_service_api.repository;

import com.rpdevelopment.user_service_api.entitie.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByRoadAndNumberAndZipCodeAndCity(
            String road,
            String number,
            String zipCode,
            String city
    );
}
