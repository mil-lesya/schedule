package com.gmail.mileshko.lesya.schedule.security;

import com.gmail.mileshko.lesya.schedule.entity.User;
import com.gmail.mileshko.lesya.schedule.repository.RoleRepository;
import com.gmail.mileshko.lesya.schedule.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String ROLE_PREFIX = "ROLE_";
    private UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("no user with such e-mail"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
        return authorities;
    }


}
