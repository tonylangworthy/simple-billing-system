package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.model.Account;
import com.langworthytech.simplebillingsystem.service.AccountService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SetupDatabaseData implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private AccountService accountService;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private AuthorityService authorityService;

    public SetupDatabaseData(
            AuthorityService authorityService,
            AccountService accountService,
            UserService userService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.accountService = accountService;
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
        Privilege updatePrivilege = authorityService.findOrCreatePrivilege("UPDATE_PRIVILEGE");
        Privilege deletePrivilege = authorityService.findOrCreatePrivilege("DELETE_PRIVILEGE");

        // Add all privileges to the admin role
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege, updatePrivilege, deletePrivilege
        );
        // Create the roles and assign privileges to each role
        Role adminRole = authorityService.findOrCreateRole("ROLE_ADMIN", adminPrivileges);
        Role userRole = authorityService.findOrCreateRole("ROLE_USER", Arrays.asList(readPrivilege));

        Optional<Account> optionalAccount = accountService.findAccountByEmail("admin@webbdealer.com");
        if(!optionalAccount.isPresent()) {

            Account newAccount = new Account();
            newAccount.setEmail("admin@webbdealer.com");
            newAccount.setCompany("Langworthy Technologies");
            newAccount.setAddress("1215 Blacktop Road");
            newAccount.setCity("Summerville");
            newAccount.setState("MO");
            newAccount.setZip("65111");
            newAccount.setPhone("555-555-5555");
            newAccount.setWebsite("langworthytech.com");
            newAccount.setActive(true);
            accountService.createAccount(newAccount);

            User user1 = new User();
            user1.setFirstName("Tony");
            user1.setLastName("Langworthy");
            user1.setPassword(passwordEncoder.encode("password"));
            user1.setEmail("tony@webbdealer.com");
            user1.setRoles(Arrays.asList(adminRole));
            user1.setEnabled(true);
            user1.setAccount(newAccount);
            newAccount.getUsers().add(user1);
            userService.createUser(user1);

            User user2 = new User();
            user2.setFirstName("Jon");
            user2.setLastName("Doe");
            user2.setPassword(passwordEncoder.encode("password"));
            user2.setEmail("jdoe@gmail.com");
            user2.setRoles(Arrays.asList(adminRole));
            user2.setEnabled(true);
            user2.setAccount(newAccount);
            newAccount.getUsers().add(user2);
            userService.createUser(user2);
        }
        alreadySetup = true;
    }

}
