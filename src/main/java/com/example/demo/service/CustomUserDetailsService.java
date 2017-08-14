package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Naver on 2017. 7. 27..
 */
@Slf4j
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.debug("load username : {}", username);
        com.example.demo.domain.User user = userRepository.findByUserId(username);
        log.debug("loaded User : {}", user);
        if(user == null) {
            throw new UsernameNotFoundException("no user");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.example.demo.domain.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUserId(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        for (Role role : roles) {
            log.debug("GET ROLE: {}", role.getRole());
            if (role.getRole().equals("USER")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            } else if (role.getRole().equals("ADMIN")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
        log.debug("HIS AUTH: {}", authorities);
        return authorities;
    }
}
