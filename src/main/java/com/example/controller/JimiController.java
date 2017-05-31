package com.example.controller;

import com.example.service.ChordService;
import com.example.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/key")
    public String getSongPageForKey(@RequestParam(value = "key", required = true) String key, Model model) {
        System.out.println(key);
        List<String> chords = keyService.getChordsFromKey(key);
        model.addAttribute("chords", chords);
        model.addAttribute("key", key);

        return "song";
    }

    @PostMapping("/song")
    public String addChordToSong(@RequestParam(value = "key", required = true) String key,
                                 @RequestParam(value = "chord", required = true) String chord,
                                 @RequestParam(value = "songChords", required = false) List<String> songChords,
                                 Model model) {
        System.out.println(key);
        if (songChords == null){
            songChords = new ArrayList<>();
        }
        songChords.add(chord);
        List<String> chords = chordService.getSuggestedChords(key, songChords);
        model.addAttribute("chords", chords);
        model.addAttribute("key", key);
        model.addAttribute("songChords", songChords);


        return "song";
    }




}
