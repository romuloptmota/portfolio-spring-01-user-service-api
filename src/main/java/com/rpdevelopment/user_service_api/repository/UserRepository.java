package com.rpdevelopment.user_service_api.repository;

import com.rpdevelopment.user_service_api.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
