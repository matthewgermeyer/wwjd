package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by MattyG on 6/14/17.
 */
@Repository
public class AuthoritiesDaoImpl implements AuthoritiesDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(String username) {
        jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?,?)",
                username,
                "Role_User");
    }
}
