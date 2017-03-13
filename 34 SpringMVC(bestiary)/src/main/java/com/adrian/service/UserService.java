package com.adrian.service;

import com.adrian.domain.objects.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User read(String userLogin);
    List<User> readAll();
    void update(String userLogin, User user);
    void delete(String userLogin);
}
