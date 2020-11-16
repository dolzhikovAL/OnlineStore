package com.DA.store.service;

import com.DA.store.model.User;

public interface UserService extends BaseService<User> {
    User getByEmail(String email);

    void setPassword(User user, String password);
}
