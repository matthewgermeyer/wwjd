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
    public List<String> getVandVIIChordsFromKey(String key) {
        List<String> chords = new ArrayList<>();
        key = key.toUpperCase();

        switch (key) {
            case "C":
                chords.add("C7");
                chords.add("G7");
                chords.add("Bo");
                break;

            case "G":
                chords.add("D7");
                chords.add("F#o");
                chords.add("G7");
                break;

            case "D":
                chords.add("A7");
                chords.add("C#o");
                chords.add("D7");
                break;

            case "A":
                chords.add("A7");
                chords.add("G#o");
                chords.add("E7");

                break;

            case "E":
                chords.add("E7");
                chords.add("B7");
                chords.add("D#o");
                break;

            case "Bb":
                chords.add("Bb7");
                chords.add("F7");
                chords.add("Ao");
                break;

            case "F":
                chords.add("F7");
                chords.add("Eo");
                chords.add("C7");
                break;

            default:
                chords.add("Am");
                chords.add("Bm");
                chords.add("Em");
                chords.add("C");
                chords.add("G");
                chords.add("D");
                break;

        }
        return chords;
    }
}
