package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.account.AccountRepository;
import com.langworthytech.simplebillingsystem.account.AccountService;
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

    private AccountService accountService;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private AuthorityService authorityService;

    public SetupUserRoles(
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

        // Add all privileges to the admin role
        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege
        );
        // Create the roles and assign privileges to each role
        Role adminRole = authorityService.findOrCreateRole("ROLE_ADMIN", adminPrivileges);
        authorityService.findOrCreateRole("ROLE_USER", Arrays.asList(readPrivilege));

        Optional<Account> optionalAccount = accountService.findAccountByEmail("admin@webbdealer.com");
        if(!optionalAccount.isPresent()) {

            Account newAccount = new Account();
            newAccount.setEmail("admin@webbdealer.com");
            newAccount.setCompany("Langworthy Technologies");
            newAccount.setAddress("100 Java Lane");
            newAccount.setCity("Coolness");
            newAccount.setState("MO");
            newAccount.setZip("65111");
            newAccount.setPhone("555-555-5555");
            newAccount.setWebsite("https://langworthytech.com");
            newAccount.setActive(true);
            accountService.createAccount(newAccount);

            User user = new User();
            user.setFirstName("Tony");
            user.setLastName("Langworthy");
            user.setPassword(passwordEncoder.encode("Acura2121"));
            user.setEmail("tony@webbdealer.com");
            user.setRoles(Arrays.asList(adminRole));
            user.setEnabled(true);
            user.setAccount(newAccount);
            newAccount.getUsers().add(user);
            userService.createUser(user);
        }



        alreadySetup = true;
    }

}
