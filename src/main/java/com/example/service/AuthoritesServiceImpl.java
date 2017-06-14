package com.example.service;

import com.example.repository.AuthoritiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MattyG on 6/14/17.
 */

@Service
public class AuthoritesServiceImpl implements AuthoritiesService {

    @Autowired
    AuthoritiesDao authoritiesDao;
    @Override
    public void add(String username) {
        authoritiesDao.add(username);

    }
}
