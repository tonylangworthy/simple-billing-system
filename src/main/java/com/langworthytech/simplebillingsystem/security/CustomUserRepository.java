package com.langworthytech.simplebillingsystem.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

    Optional<CustomUser> findByEmail(String email);
}
