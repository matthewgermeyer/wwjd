package com.example.service;

import com.example.domain.Song;
import com.example.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SongServiceTest {
    @Autowired
    SongService songService;
    @Autowired
    UserService userService;

    @Test
    public void testAddSongs() {
        //create user via user service
        User testUser = new User();
        String name = Long.toString(System.currentTimeMillis());
        testUser.setUsername(name);
        testUser.setPassword("testuserpass");
        testUser.setEnabled(true);

        //add user
        userService.add(testUser);

        //create song
        Song testSong = new Song();
        testSong.setTitle("testSong");
        testSong.setKey("C");
        testSong.setGenre("blues");
        testSong.setUsername(name);

        //add song
        songService.add(testSong);

        //find song for user
        List<Song> songs = songService.findAllByUsername(name);
        Assert.assertTrue(songs.size()==1);
        boolean found = false;
        for (Song song : songs) {
            if (testSong.equals(song)) {
                found = true;
            }
        }
        Assert.assertTrue(found);

        //create another song

        Song testSong2 = new Song();
        testSong2.setTitle("testSong2");
        testSong2.setKey("D");
        testSong2.setGenre("popRock");
        testSong2.setUsername(name);
        //add it
        songService.add(testSong2);

        //find songs again, ensure there are two.
        songs = songService.findAllByUsername(name);
        Assert.assertTrue(songs.size()==2);
         found = false;
        for (Song song : songs) {
            if (testSong2.equals(song)) {
                found = true;
            }
        }
        Assert.assertTrue(found);



    }

}
