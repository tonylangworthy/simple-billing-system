package com.langworthytech.simplebillingsystem.security;

import com.langworthytech.simplebillingsystem.entities.Authority;
import com.langworthytech.simplebillingsystem.entities.CustomUser;
import com.langworthytech.simplebillingsystem.models.RegistrationFormModel;
import com.langworthytech.simplebillingsystem.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService {

    private PasswordEncoder passwordEncoder;

    private CustomUserRepository userRepository;

    @Autowired
    public CustomUserService(PasswordEncoder passwordEncoder, CustomUserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public CustomUser registerNewUser(RegistrationFormModel registrationModel) {
        String encodedPassword = passwordEncoder.encode(registrationModel.getConfirmPassword());

        CustomUser user = new CustomUser();
        user.setEnabled(Boolean.TRUE);
        user.setPassword(encodedPassword);
        user.setEmail(registrationModel.getEmail());
        user.setFirstName(registrationModel.getFirstName());
        user.setLastName(registrationModel.getLastName());

        Authority authority = new Authority();
        authority.setAuthority("USER");
        authority.setCustomUser(user);
        user.getAuthorities().add(authority);
        userRepository.save(user);

        return user;
    }
}
