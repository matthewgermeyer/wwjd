package com.example.controller;

import com.example.common.Util;
import com.example.domain.HookTheorySong;
import com.example.domain.Song;
import com.example.service.ChordService;
import com.example.service.HookTheoryService;
import com.example.service.KeyService;
import com.example.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class JimiController {

    @Autowired
    KeyService keyService;
    @Autowired
    ChordService chordService;
    @Autowired
    SongService songService;
    @Autowired
    Util util;
    @Autowired
    HookTheoryService hookTheoryService;

    @GetMapping("/project")
    public String project() {
        return "project";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/key")
    public String songPageFromKey(@RequestParam(value = "key",
            required = true) String key, Model model) {
        List<String> chords = chordService.getBasicChords(key);
        model.addAttribute("chords", chords);
        model.addAttribute("key", key);
        return "song";
    }

    @GetMapping("/genre")
    public String getGenre(
            @RequestParam(value = "key", required = true) String key,@RequestParam(value = "genre", required = true) String genre,
                           Model model) {

        List<String>  songChords = util.chordsFromGenre(genre, key);
        util.genreForModel(genre, model);
        List<HookTheorySong> hookSongs = hookTheoryService.getHookTheorySongs(genre);


        model.addAttribute("genre", genre);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);
        model.addAttribute("hookSongs", hookSongs);

        return "song";
    }

    @RequestMapping("/newSong")
    public String save(@RequestParam(value = "key", required = true) String key,
                       @RequestParam(value = "genre", required = true) String genre,
                       Model model) {

        List<String> songChords = util.chordsFromGenre(genre, key);
        util.genreForModel(genre, model);

        model.addAttribute("genre", genre);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);
        return "nameSong";
    }

    @PostMapping("/save")
    public String giveNewSongItsTitleSave(
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "genre", required = true) String genre,
            @RequestParam(value = "title", required = true) String title,
            Model model) {

        //add the song given 3 parameters
        songService.add(title, key, genre);

        //get list of songs from songservice
        List<Song> songs = songService.findAllByUsername();
        model.addAttribute("songs", songs);
        return "songManagement";
    }

    @GetMapping("/manageSongs")
    public String seeAllSongsForUser(Model model) {
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

        List<String> suggestedChords = keyService.getVandVIIChordsFromKey(key);
        suggestedChords.addAll(chordService.getBasicChords(key));
        //Convert to a set to remove any duplications
        Set<String> suggestedChordSet = new TreeSet<>(suggestedChords);

        model.addAttribute("chords", suggestedChordSet);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }

}
