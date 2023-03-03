package com.ssummit.service.userDetails;
import com.ssummit.model.User;
import com.ssummit.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String adminUserName = "admin";
        String adminPassword = "$2a$10$2hT9EAnN4e.mlsUVX2h7COtvkS3pjd/2T.4lsTSp4FJOYY1BSgGoO";
        String adminRole = "ROLE_ADMIN";
        if (username.equals(adminUserName)) {
           return new CustomUserDetails(null, username, adminPassword,
                   List.of(new SimpleGrantedAuthority(adminRole)));
        }
        else {
            User user = userRepository.findUserByLogin(username);
            List<GrantedAuthority> authorities = new ArrayList<>();
            switch (user.getRole().getId().intValue()) {
                case 2 -> authorities.add(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));
                case 3 -> authorities.add(new SimpleGrantedAuthority("ROLE_GUIDE"));
                case 4 -> authorities.add(new SimpleGrantedAuthority("ROLE_SPECTATOR"));
            }
            return new CustomUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
        }
    }
}

