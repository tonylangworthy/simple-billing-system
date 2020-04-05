package com.langworthytech.simplebillingsystem.security;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class SetupUserRoles implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthorityService authorityService;

    private RoleRepository roleRepository;

    private PrivilegeRepository privilegeRepository;

    public SetupUserRoles(
            AuthorityService authorityService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
    }


    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if(alreadySetup) {
            return;
        }

        // Create the privileges
        Privilege readPrivilege = authorityService.findOrCreatePrivilege("READ_PRIVILEGE");
        Privilege writePrivilege = authorityService.findOrCreatePrivilege("WRITE_PRIVILEGE");

        // Add all privileges to the admin role
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege
        );
        // Create the roles and assign privileges to each role
        Role adminRole = authorityService.findOrCreateRole("ROLE_ADMIN", adminPrivileges);
        authorityService.findOrCreateRole("ROLE_USER", Arrays.asList(readPrivilege));

        User user = new User();
        user.setFirstName("Tony");
        user.setLastName("Langworthy");
        user.setPassword(passwordEncoder.encode("Acura2121"));
        user.setEmail("admin@webbdealer.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

}
