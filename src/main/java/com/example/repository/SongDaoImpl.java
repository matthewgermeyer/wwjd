package com.example.repository;

import com.example.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */

@Repository
public class SongDaoImpl implements SongDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Song song) {
        jdbcTemplate.update("INSERT INTO song(title, keyy, genre, username) VALUES (?,?,?,?)",
                song.getTitle(),
                song.getKey(),
                song.getGenre(),
                song.getUsername());
}

    @Override
    public List<Song> findAllByUsername(String username) {
        List<Song> songs = this.jdbcTemplate.query(
                "select * from song where username = ?",
                new SongMapper(), username);

        return songs;
    }

private static class SongMapper implements RowMapper<Song> {

    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        Song song = new Song();
        song.setId(rs.getInt("id"));
        song.setTitle(rs.getString("title"));
        song.setKey(rs.getString("keyy"));
        song.setGenre(rs.getString("genre"));
        song.setUsername(rs.getString("username"));
        return song;
    }
}


}
