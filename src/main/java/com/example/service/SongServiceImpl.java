package com.example.service;

import com.example.domain.Song;
import com.example.repository.SongDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
@Service
public class SongServiceImpl implements  SongService{

    @Autowired
    SongDao songDao;

    @Override
    @Transactional
    public void add(Song song) {
        songDao.add(song);

    }

    @Override
    public List<Song> findAllByUsername(String username){
        return songDao.findAllByUsername(username);

    }
}
