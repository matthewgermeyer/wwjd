package com.example.repository;

import com.example.domain.Song;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
public interface SongDao {
    void add(Song song);
    List<Song> findAllByUsername(String username);

}
