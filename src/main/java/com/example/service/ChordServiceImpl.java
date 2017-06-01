package com.example.service;

import domain.Chord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
@Service
public class ChordServiceImpl implements ChordService {

    @Override
    public List<String> getSuggestedChords(String key, List<String> songChords) {
        List<String> chords = new ArrayList<>();
        chords.add("E7");
        chords.add("D7");
        chords.add("Em");
        chords.add("Am");
        chords.add("C");
        chords.add("G");
        chords.add("D");
        return chords;
    }

    @Override
    public void addChord() {

    }

    @Override
    public void deleteChord() {

    }

    @Override
    public List<Chord> listChordsForSong() {
        return null;
    }
}
