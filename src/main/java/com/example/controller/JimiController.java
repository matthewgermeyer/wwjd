package com.example.controller;

import com.example.common.Util;
import com.example.domain.Song;
import com.example.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class JimiController {
    private static final Logger logger = LoggerFactory.getLogger(JimiController.class);

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

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @PostMapping("/project/key")
    public String keyChosen(@RequestParam(value = "key",
            required = true) String key, Model model) {
        model.addAttribute("key", key);
        return "song";
    }

    @GetMapping("/hookchord")
    public String hookChordAPI(Model model) {
        model.addAttribute("hookChords", hookTheoryService.getHookTheoryChords(""));

        return "hookchord";
    }

    @PostMapping("/hookchord/key")
    public String songKeyAdded(@RequestParam(value = "key",
            required = true) String key, Model model) {


        model.addAttribute("key", key);
        model.addAttribute("chordChoice", chordService.getBasicChords(key));
        model.addAttribute("hookChords", hookTheoryService.getHookTheoryChords(""));

        return "hookchord";
    }

    @PostMapping("/hookchord/key/chord")
    public String songChordAdded(@RequestParam(value = "key",
            required = true) String key, @RequestParam(value = "chordToAdd", required = true) String chordToAdd, @RequestParam(value = "songChords", required = false) List<String> songChords, Model model) {

        songChords.add(chordToAdd);
        System.out.println(chordService.getBasicChords(key));

//        AnalyzeProgression(songChords);



        model.addAttribute("key", key);
        model.addAttribute("songChords", util.cleanUpSongChords(songChords));
        model.addAttribute("chordChoice", chordService.getBasicChords(key));
        model.addAttribute("hookChords", hookTheoryService.getHookTheoryChords(""));

        return "hookchord";
    }



    @GetMapping("/genre")
    public String getSonginGenre(
            @RequestParam(value = "key", required = true) String key,@RequestParam(value = "genre", required = true) String genre, Model model) {

        List<String> songChords = util.songChordsFromGenre(genre, key);

        util.genreOnPlayButton(genre, model);  // Gives a label to play button
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




    //Exception Handling --
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleDefaultErrors(final Exception exception, final HttpServletRequest request, final HttpServletResponse resp) {
        logger.warn(exception.getMessage() + "\n" + stackTraceAsString(exception));
        return new ModelAndView("error", "message", exception.getMessage());
    }

    private String stackTraceAsString(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        return sw.toString();
    }
}
