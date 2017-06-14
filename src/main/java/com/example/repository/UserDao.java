package com.example.repository;

import com.example.domain.User;

/**
 * Created by MattyG on 6/8/17.
 */
public interface UserDao {
    void add(User user);
    boolean isFound(String username);
    void add(String username);
}
