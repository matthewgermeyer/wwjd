package com.example.controller;

import com.example.service.ChordService;
import com.example.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class JimiController {

    @Autowired
    KeyService keyService;
    @Autowired
    ChordService chordService;

    @GetMapping("/project")
    public String startNewProject() {
        return "project";
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
    public String getBlues(@RequestParam(value = "key", required = true) String key,
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
            model.addAttribute("genre", "Simple Rock");

        } else if (genre.equals("blues")){
            model.addAttribute("genre", "blues");
        } else if (genre.equals("popRock")){
            model.addAttribute("genre","Pop-Rock");
        } else {
            model.addAttribute("genre","Soulful tune");
        }

        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }

    @PostMapping("/song")
    public String addChordToSong(@RequestParam(value = "key", required = true) String key,
                                 @RequestParam(value = "chord", required = true) String chord,
                                 @RequestParam(value = "songChords", required = false) List<String> songChords,
                                 Model model) {
        if (songChords == null){
            songChords = new ArrayList<>();
        }
        //Add picked chord from list to songChords.
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

    @PostMapping("/jimi")
    public String addJimiChord(@RequestParam(value = "key", required = true) String key,
                               @RequestParam(value = "songChords", required = false) List<String> songChords,
                               Model model) {

        if (songChords == null){
            songChords = new ArrayList<>();
        }

        //Add the next chord via jimiPickNextChord Method.
        chordService.jimiPickNextChord(songChords);

        List<String> suggestedChords = keyService.getVandVIIChordsFromKey(key);
        suggestedChords.addAll(chordService.getBasicChords(key));

        Set<String> suggestedChordSet = new TreeSet<>(suggestedChords);

        model.addAttribute("chords", suggestedChordSet);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }
}
