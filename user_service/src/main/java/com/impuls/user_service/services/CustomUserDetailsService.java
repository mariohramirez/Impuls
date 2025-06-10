package com.impuls.user_service.services;

import com.impuls.user_service.model.User;
import com.impuls.user_service.model.gateway.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null){
            throw new UsernameNotFoundException("El usuario no fue encontrado con el correo: "+username);
        }

        // Obtener los roles del usuario (en este caso un solo rol)
        String role = user.getRole().getName().toString(); // "ROLE_USUARIO", "ROLE_ADMIN", etc.
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(role)
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked
                authorities
        );
    }
}
