package com.quatrohaus.usermanagement.service;

import com.quatrohaus.usermanagement.model.User;
import com.quatrohaus.usermanagement.model.UserProfile;
import com.quatrohaus.usermanagement.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userDetailsService")
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepo;

    static final Logger logger = LoggerFactory.getLogger(UserRepositoryUserDetailsService.class);

    public UserRepositoryUserDetailsService(UserJpaRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username)   throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return  user;
        }
        logger.info("User  not found");
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
      /*return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                 true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }*/

}