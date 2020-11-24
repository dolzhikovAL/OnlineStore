package com.DA.store.controller.auth;

import com.DA.store.model.User;
import com.DA.store.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Uer with name %s not exist", username)));
        return new UserPrincipal(user);
    }
}
