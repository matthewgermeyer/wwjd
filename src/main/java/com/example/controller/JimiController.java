package com.example.controller;

import com.example.common.Util;
import com.example.domain.Song;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class JimiController {

    @Autowired
    UserService userService;
    @Autowired
    ChordService chordService;
    @Autowired
    SongService songService;
    @Autowired
    Util util;
    @Autowired
    HookTheoryService hookTheoryService;
    @Autowired
    AuthoritiesService authoritiesService;

    @GetMapping("/project")
    public String project() {
        return "project";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/project/key")
    public String noKey(@RequestParam(value = "key",
            required = false) String key, Model model) {
        model.addAttribute("key", key);
        return "song";
    }

    @PostMapping("/project/key")
    public String keyChosen(@RequestParam(value = "key",
            required = true) String key, Model model) {
        model.addAttribute("key", key);
        return "song";
    }

    @GetMapping("/genre")
    public String getSonginGenre(
            @RequestParam(value = "key", required = true) String key,@RequestParam(value = "genre", required = true) String genre, Model model) {

        List<String> songChords = util.songChordsFromGenre(genre, key);


        //adds genretext to playbutton in model
        util.genreOnPlayButton(genre, model);
        model.addAttribute("genre", genre);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);
        model.addAttribute("hookSongs", hookTheoryService.getHookTheorySongs(genre));

        return "song";
    }

    @RequestMapping("/newSong")
    public String save(@RequestParam(value = "key", required = true) String key,
                       @RequestParam(value = "genre", required = true) String genre,
                       Model model) {

        List<String> songChords = util.songChordsFromGenre(genre, key);
        util.genreOnPlayButton(genre, model);

        model.addAttribute("genre", genre);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);
        return "nameSong";
    }

    @PostMapping("/save")
    public String saveSongTitle(
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "genre", required = true) String genre,
            @RequestParam(value = "title", required = true) String title,
            Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("anonymousUser".equals(authentication.getName())){
            return "/login";
        }

        songService.add(title, key, genre);
        List<Song> songs = songService.findAllByUsername();
        model.addAttribute("songs", songs);
        return "songManagement";
    }

    @GetMapping("/manageSongs")
    public String seeAllSongsForUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("anonymousUser".equals(authentication.getName())){
            return "/login";
        }

        if (! userService.isFound(authentication.getName())){
            userService.add(authentication.getName());
            authoritiesService.add(authentication.getName());

        }

        model.addAttribute("songs", songService.findAllByUsername());
        return "songManagement";
    }

    @PostMapping("/mySongs/delete")
    public String deleteSong(@RequestParam(value = "songId", required = true) int songId, Model model, HttpServletResponse response) throws IOException {
        songService.delete(songId);
        model.addAttribute("songs", songService.findAllByUsername());
        return "songManagement";

    }

    @PostMapping("/mySongs/update")
    public String updateSong(@RequestParam(value = "songId", required = true) int songId,
                             @RequestParam(value="songTitle", required = true) String songTitle, Model model, HttpServletResponse response) throws IOException {
        songService.update(songId, songTitle);
        model.addAttribute("songs", songService.findAllByUsername());
        return "songManagement";

    }

    @PostMapping("/song")
    public String addChordToSong(
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "chord", required = true) String chord,
            @RequestParam(value = "songChords", required = false) List<String> songChords, Model model) {
        if (songChords == null) {
            songChords = new ArrayList<>();
        }
        songChords.add(chord);
        List<String> suggestedChords = chordService.getBasicChords(key);
        //Convert to a set to remove any duplications
        Set<String> suggestedChordSet = new TreeSet<>(suggestedChords);

        model.addAttribute("chords", suggestedChordSet);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }

}
