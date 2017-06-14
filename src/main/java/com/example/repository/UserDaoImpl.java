package com.example.repository;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    @Override
    public boolean isFound(String username){
        String sql = "select username from users where username = ?";
        try {
             jdbcTemplate.queryForObject(
                    sql, String.class, username);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }


    }
    @Override
    public void add(String username) {
        jdbcTemplate.update("INSERT INTO users(username, password, enabled) VALUES (?,?,?)",
                username,
                null,
                true);
    }


}
