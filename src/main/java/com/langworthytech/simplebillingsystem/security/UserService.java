package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.account.Account;
import com.langworthytech.simplebillingsystem.account.AccountRepository;
import com.langworthytech.simplebillingsystem.account.RegistrationFormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private AccountRepository accountRepository;

    private UserRepository userRepository;

    private AuthorityService authorityService;

    @Autowired
    public UserService(
            PasswordEncoder passwordEncoder,
            AccountRepository accountRepository,
            UserRepository userRepository,
            AuthorityService authorityService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    @Transactional
    public User registerNewUser(RegistrationFormModel registrationModel) throws EmailExistsException, RoleNotFoundException {

        if(emailExists(registrationModel.getEmail())) {
            throw new EmailExistsException("There is already an account using that email address: " + registrationModel.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(registrationModel.getConfirmPassword());

        Account account = new Account();
        account.setEmail(registrationModel.getEmail());
        account.setActive(true);
        accountRepository.save(account);


        User user = new User();
        user.setEnabled(Boolean.TRUE);
        user.setPassword(encodedPassword);
        user.setEmail(registrationModel.getEmail());
        user.setFirstName(registrationModel.getFirstName());
        user.setLastName(registrationModel.getLastName());
        user.setRoles(Arrays.asList(authorityService.findRoleByName("ROLE_ADMIN")
                .orElseThrow(() -> new RoleNotFoundException("This role does not exist!"))));
        user.setAccount(account);
        account.getUsers().add(user);
        userRepository.save(user);

        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
