package com.adrian.domain.repository;

import com.adrian.domain.objects.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    User read(String userLogin);
    List<User> readAll();
    void update(String userLogin, User user);
    void delete(String userLogin);
}
