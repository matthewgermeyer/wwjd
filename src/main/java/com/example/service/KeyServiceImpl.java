package com.example.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
@Service
public class KeyServiceImpl implements KeyService{

    @Override
    public List<String> getChordsFromKey(String key) {
        List<String> chords = new ArrayList<>();
        chords.add("D7");
        chords.add("A7");
        chords.add("C");
        chords.add("D");
        chords.add("G");
        chords.add("Am");
        chords.add("Em");
        return chords;
    }
}
