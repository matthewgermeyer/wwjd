package com.example.repository;

import com.example.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
        StringBuilder sb = new StringBuilder();
        boolean addSeparator = false;

        for (String chord : song.getChords()) {
            if (addSeparator) {
                sb.append("|");
            } else {
                addSeparator = true;
            }
            sb.append(chord);
        }

        jdbcTemplate.update("INSERT INTO song(title, keyy, genre, username, chords) VALUES (?,?,?,?,?)",
                song.getTitle(),
                song.getKey(),
                song.getGenre(),
                song.getUsername(),
                sb.toString());
}

    @Override
    public List<Song> findAllByUsername(String username) {
        List<Song> songs = this.jdbcTemplate.query(
                "select * from song where username = ?",
                new SongMapper(), username);

        return songs;
    }

    @Override
    public void delete(int id){
        String sql = "delete from song where id = ?";
        jdbcTemplate.update(sql, id);

        System.out.println("==============");
        System.out.println("deleting song -> " + id);

    }
    @Override
    public void update(int id, String title){
        String sql = "update song SET title = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, id);

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

        String chords = rs.getString("chords");
        String[] chords2 = chords.split("\\|");
        System.out.println("======================");
        System.out.println(Arrays.toString(chords2));
        song.setChords(Arrays.asList(chords2));

        return song;
    }
}



}
