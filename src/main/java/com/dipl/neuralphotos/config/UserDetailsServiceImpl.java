package com.dipl.neuralphotos.config;

import com.dipl.neuralphotos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.dipl.neuralphotos.model.User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " was not found!");
        }
        String role = user.getRole().name();
        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        return new User(user.getEmail(), user.getPassword(), user.isActive(),
                true, true, true, grantList);
    }
}
