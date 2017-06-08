package com.example.service;

import com.example.domain.Song;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
public interface SongService {

    void add(Song song);
    List<Song> findAllByUsername(String username);

}
