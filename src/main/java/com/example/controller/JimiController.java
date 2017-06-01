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
    public String getNewProject() {
        return "project";
    }

    @PostMapping("/keyurl")
    public String songPageFromKey(@RequestParam(value = "keyhttpparam",
            required = true) String key, Model model) {
        List<String> chords = keyService.getChordsFromKey(key);
        model.addAttribute("chords", chords);
        model.addAttribute("key", key);
        return "song";
    }

    @PostMapping("/songurl")
    public String addChordToSong(@RequestParam(value = "keyparam", required = true) String key,
                                 @RequestParam(value = "chordparam", required = true) String chord,
                                 @RequestParam(value = "songChordsparam", required = false) List<String> songChords,
                                 Model model) {
        System.out.println(key);
        if (songChords == null){
            songChords = new ArrayList<>();
        }
        songChords.add(chord);
        List<String> suggestedChords = keyService.getChordsFromKey(key);
        suggestedChords.addAll(chordService.getSuggestedChords(key, songChords));

        //Convert to a set to remove duplication
        Set<String> suggestedChordSet = new TreeSet<>(suggestedChords);
        model.addAttribute("chords", suggestedChordSet);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);

        return "song";
    }




}
