package com.example.controller;

import com.example.domain.Song;
import com.example.service.ChordService;
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
    public String getGenre(@RequestParam(value = "key", required = true) String key,
                                 @RequestParam(value = "genre", required = true) String genre,
                                 Model model) {
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
        if (genre.equals("classicRock")){
            model.addAttribute("genretext", "Simple Rock");

        } else if (genre.equals("blues")){
            model.addAttribute("genretext", "blues");
        } else if (genre.equals("popRock")){
            model.addAttribute("genretext","Pop-Rock");
        } else {
            model.addAttribute("genretext","Soulful tune");
        }

        model.addAttribute("genre", genre);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }

    @RequestMapping("/newSong")
    public String save(@RequestParam(value = "key", required = true) String key,
                       @RequestParam(value = "genre", required = true) String genre,
                       Model model) {

        System.out.println("delete this lagter");

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
        if (genre.equals("classicRock")){
            model.addAttribute("genretext", "Simple Rock");

        } else if (genre.equals("blues")){
            model.addAttribute("genretext", "blues");
        } else if (genre.equals("popRock")){
            model.addAttribute("genretext","Pop-Rock");
        } else {
            model.addAttribute("genretext","Soulful tune");
        }
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


        model.addAttribute("songs",songs);


        return "songManagement";
    }

    @GetMapping("/manageSongs")
    public String seeAllSongsForUser(Model model) {
        List<Song> songs = songService.findAllByUsername();



        model.addAttribute("songs", songService.findAllByUsername());

        return "songManagement";
    }

    @PostMapping("/song")
    public String addChordToSong(
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "chord", required = true) String chord,
            @RequestParam(value = "songChords", required = false) List<String> songChords, Model model) {

        if (songChords == null){
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

//    @PostMapping("/jimi")
//    public String addJimiChord(@RequestParam(value = "key", required = true) String key,
//                               @RequestParam(value = "songChords", required = false) List<String> songChords,
//                               Model model) {
//        if (songChords == null){
//            songChords = new ArrayList<>();
//        }
//        //Add the next chord via jimiPickNextChord Method.
//        chordService.jimiPickNextChord(songChords);
//        List<String> suggestedChords = keyService.getVandVIIChordsFromKey(key);
//        suggestedChords.addAll(chordService.getBasicChords(key));
//        Set<String> suggestedChordSet = new TreeSet<>(suggestedChords);
//        model.addAttribute("chords", suggestedChordSet);
//        model.addAttribute("key", key);
//        model.addAttribute("songChords", songChords);
//        return "song";
//    }
}
