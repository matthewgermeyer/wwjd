package com.example.common;

import com.example.service.ChordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by MattyG on 6/9/17.
 */
@Service
public class Util {

    @Autowired
    ChordService chordService;

    public List<String> songChordsFromGenre(String genre, String key){

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
        return songChords;
    }

    //genretext for model
    public Model genreOnPlayButton(String genre, Model model){

        if (genre.equals("classicRock")) {
            model.addAttribute("genretext", "Simple Rock");

        } else if (genre.equals("blues")) {
            model.addAttribute("genretext", "blues");
        } else if (genre.equals("popRock")) {
            model.addAttribute("genretext", "Pop-Rock");
        } else {
            model.addAttribute("genretext", "Soulful tune");
        }
    return model;
    }

    //URL fragment for HookTheory API
    public String getGenreUrl(String genre){
    String genreURL;

        switch (genre){
            case "blues":
                genreURL ="1,4,5";
                break;
            case "popRock":
                genreURL ="1,5,6,4";
                break;
            case "soulful":
                genreURL ="1,6,4,5";
                break;
            case "classicRock":
                genreURL ="1,4,5,4";
                break;
            default:
                genreURL = "1";
        }

        return genreURL;
    }



}
