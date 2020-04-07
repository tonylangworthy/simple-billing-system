package com.langworthytech.simplebillingsystem.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserRepository userRepository;

    private AuthorityService authorityService;

    public CustomUserDetailsService(UserRepository userRepository, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
    }

    /**
     * <p>Loads the UserDetails from the username provided.
     * </p>
     * @param email the user's submitted username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        logger.info("Running the loadUserByUsername (CustomUserDetailsService implementation)");

        final Optional<User> optionalCustomUser = this.userRepository.findByEmail(email);
        logger.info("Loading the UserDetails");

        User user = optionalCustomUser.orElseThrow(() -> new UsernameNotFoundException(email));

        Role userRole = authorityService.findRoleByName("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("User role not found!"));

        CustomUserDetails userDetails = new CustomUserDetails(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAccount(),
                true,
                true,
                true,
                true,
                getAuthorities(user)
        );
        return userDetails;
    }

    private static Collection<? extends  GrantedAuthority> getAuthorities(User user) {
        logger.info("getAuthorities for user email: " + user.getEmail());

        Collection<Role> roles = user.getRoles();
        List<String> privileges = getPrivileges(roles);
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(privileges);
        return grantedAuthorities;


//        String[] userRoles = user.getRoles().stream().map(authority -> authority.getPrivileges()).toArray(String[]::new);
//        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//        return authorities;
    }

    private static List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        roles.forEach(role -> {
            role.getPrivileges().forEach(privilege -> {
                privileges.add(privilege.getName());
            });
        });
        return privileges;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        privileges.forEach(privilege -> {
            authorities.add(new SimpleGrantedAuthority(privilege));
        });
        return authorities;
    }

}
