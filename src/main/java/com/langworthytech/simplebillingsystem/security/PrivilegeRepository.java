package com.langworthytech.simplebillingsystem.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);
}
