package com.example.service;

import com.example.domain.Song;
import com.example.repository.SongDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    ChordService chordService;

    @Override
    @Transactional
    public void add(Song song) {
        songDao.add(song);

    }

    @Override
    public List<Song> findAllByUsername(String username){
        return songDao.findAllByUsername(username);

    }

    @Override
    public List<Song> findAllByUsername(){
        //find the logged in user..
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return songDao.findAllByUsername(currentPrincipalName);

    }

    @Transactional
    public void add(String title, String key, String genre){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("==================");
        System.out.println(currentPrincipalName);

        List<String> songChords;
        switch (genre){
            case "blues":
                songChords = chordService.generateBlues(key);
                break;
            case "popRock":
                songChords = chordService.generatePopRock(key);
                break;
            case "soulful":
                songChords = chordService.generateSoulful(key);
                break;
            case "classicRock":
                songChords = chordService.generateSimpleRock(key);
                break;
            default:
                songChords = chordService.generateBlues(key);
        }

        Song song = new Song();
        song.setTitle(title);
        song.setKey(key);
        song.setGenre(genre);
        song.setUsername(currentPrincipalName);
        song.setChords(songChords);

        songDao.add(song);

    }

}
