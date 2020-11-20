package com.DA.store.service.impl;

import com.DA.store.controller.exeption.UserAlreadyExistsException;
import com.DA.store.controller.exeption.UserNotExistsException;
import com.DA.store.model.User;
import com.DA.store.model.UserRole;
import com.DA.store.model.UserStatus;
import com.DA.store.repository.UserRepository;
import com.DA.store.service.UserService;
import lombok.Data;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public @Data
class UserServerImpl implements UserService {
    private static final Logger LOGGER= LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;




    @Override
    public List<User> getAll() {
        LOGGER.debug("Get All Users");
        return userRepository.findAll();
    }

    @Override
    public User getById(UUID id) {
        LOGGER.debug("Get user by id:" + id);
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotExistsException(String.format("User with id = %s not found ",id )));
    }

    @Override
    public void save(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw  new UserAlreadyExistsException("There is an account with that email address : " + user.getEmail());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.ROLE_CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        LOGGER.debug(String.format(("Get user with email="+email )));
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotExistsException("No  matched found with email--"));
    }

    @Override
    public void setPassword(User user, String password) {
        String passwordHash=bCryptPasswordEncoder.encode(password);
        user.setPassword(passwordHash);
    }

    public String getUserName(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
