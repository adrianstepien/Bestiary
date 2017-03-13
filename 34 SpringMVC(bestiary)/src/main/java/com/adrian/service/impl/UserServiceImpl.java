package com.adrian.service.impl;

import com.adrian.domain.objects.User;
import com.adrian.domain.repository.UserRepository;
import com.adrian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        return userRepository.create(user);
    }

    public User read(String userLogin){
        return userRepository.read(userLogin);
    }

    public List<User> readAll(){
        return userRepository.readAll();
    }

    public void update(String userLogin, User user){
        userRepository.update(userLogin, user);
    }

    public void delete(String userLogin){
        userRepository.delete(userLogin);
    }
}
