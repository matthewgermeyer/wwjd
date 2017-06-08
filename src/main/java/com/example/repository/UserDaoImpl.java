package com.example.repository;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by MattyG on 6/8/17.
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO users(username, password, enabled) VALUES (?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.isEnabled());

    }
}
