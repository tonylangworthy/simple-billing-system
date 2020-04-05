package com.langworthytech.simplebillingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthorityService {

    private RoleRepository roleRepository;

    private PrivilegeRepository privilegeRepository;

    @Autowired
    public AuthorityService(
            RoleRepository roleRepository,
            PrivilegeRepository privilegeRepository
    ) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    public Privilege findOrCreatePrivilege(String privilegeName) {

        Optional<Privilege> optionalPrivilege = privilegeRepository.findByName(privilegeName);
        return optionalPrivilege.orElseGet(() -> privilegeRepository.save(new Privilege(privilegeName)));
    }

    @Transactional
    public Role findOrCreateRole(String roleName, Collection<Privilege> privileges) {
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        return optionalRole.orElseGet(() -> roleRepository.save(new Role(roleName, privileges)));
    }

    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
