package com.example.service;

import com.example.domain.Song;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
public interface SongService {

    void add(Song song);
    void add(String title, String key, String genre);
    List<Song> findAllByUsername(String username);
    List<Song> findAllByUsername();
    void delete(int id);
    void update(int id, String title);


}
