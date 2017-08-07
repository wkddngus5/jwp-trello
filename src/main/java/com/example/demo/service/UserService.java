package com.example.demo.service;

import com.example.demo.domain.User;

/**
 * Created by Naver on 2017. 7. 31..
 */
public interface UserService {
    public User findUserById(String userId);
    public void saveUser(User user);
}
