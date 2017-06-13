package com.example.service;

import com.example.domain.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MattyG on 6/8/17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);

    }
}
