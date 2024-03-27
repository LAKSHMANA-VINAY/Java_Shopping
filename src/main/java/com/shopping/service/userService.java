package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.entity.userEntity;
import com.shopping.repository.userRepository;

@Service
public class userService {

    @Autowired
    private userRepository userRepository;

    @Transactional
    public boolean addUserShopping(userEntity userShopping) {
        try {
            userRepository.insertUser(userShopping.getUserName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
