package com.langworthytech.simplebillingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private AuthorityService authorityService;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityService authorityService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    public User registerNewUser(RegistrationFormModel registrationModel) throws EmailExistsException, RoleNotFoundException {

        if(emailExists(registrationModel.getEmail())) {
            throw new EmailExistsException("There is already an account using that email address: " + registrationModel.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(registrationModel.getConfirmPassword());

        User user = new User();
        user.setEnabled(Boolean.TRUE);
        user.setPassword(encodedPassword);
        user.setEmail(registrationModel.getEmail());
        user.setFirstName(registrationModel.getFirstName());
        user.setLastName(registrationModel.getLastName());
        user.setRoles(Arrays.asList(authorityService.findRoleByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("This role does not exist!"))));

        userRepository.save(user);

        return user;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
