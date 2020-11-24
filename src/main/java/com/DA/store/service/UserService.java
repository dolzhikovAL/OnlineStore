package com.DA.store.service;

import com.DA.store.model.User;

import java.util.List;

public interface UserService extends BaseService<User> {
    User getByEmail(String email);

    List<User> getAll();

    void setPassword(User user, String password);
}
