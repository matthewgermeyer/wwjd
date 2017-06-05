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
