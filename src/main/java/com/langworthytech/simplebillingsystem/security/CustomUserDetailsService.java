package com.langworthytech.simplebillingsystem.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    /**
     * <p>Loads the UserDetails from the username provided.
     * </p>
     * @param username the user's submitted username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("Running the loadByUsername (User Details Service)");

        final Optional<CustomUser> optionalCustomUser = this.customUserRepository.findByEmail(username);
        logger.info("Loading the UserDetails");

        CustomUser customUser = optionalCustomUser.orElseThrow(() -> new UsernameNotFoundException(username));


        logger.info(customUser.getEmail());

            CustomUserDetails userDetails = new CustomUserDetails(
                    customUser.getFirstName(),
                    customUser.getLastName(),
                    customUser.getEmail(),
                    customUser.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    getAuthorities(customUser)
            );
            return userDetails;
    }

    private static Collection<? extends  GrantedAuthority> getAuthorities(CustomUser customUser) {
        String[] userRoles = customUser.getAuthorities().stream().map(authority -> authority.getAuthority()).toArray(String[]::new);
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
