package com.DA.store.service;

import com.DA.store.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService extends BaseService<User> {
    User getByEmail(String email);
List<User> getAll();
void save(User user);
void delete(UUID id);
User update(User user);
    void setPassword(User user, String password);
}
