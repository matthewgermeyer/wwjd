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
    //Takes a String key -> List called chords.
    public List<String> getChordsFromKey(String key) {
        List<String> chords = new ArrayList<>();
        key = key.toUpperCase();

        switch (key) {
            case "C":
                chords.add("C");
                chords.add("Dm");
                chords.add("Em");
                chords.add("F");
                chords.add("G7");
                chords.add("Am");
                chords.add("Bo");
                break;

            case "G":
                chords.add("G");
                chords.add("Am");
                chords.add("Bm");
                chords.add("C");
                chords.add("D7");
                chords.add("Em");
                chords.add("F#o");
                break;

            case "D":
                chords.add("D");
                chords.add("Em");
                chords.add("F#m");
                chords.add("G");
                chords.add("A7");
                chords.add("Bm");
                chords.add("C#o");
                break;

            case "A":
                chords.add("A");
                chords.add("Bm");
                chords.add("C#m");
                chords.add("D");
                chords.add("E7");
                chords.add("F#m");
                chords.add("C#o");
                break;

            case "E":
                chords.add("E");
                chords.add("F#m");
                chords.add("G#m");
                chords.add("A");
                chords.add("B7");
                chords.add("C#m");
                chords.add("D#o");
                break;

            case "B":
                chords.add("B");
                chords.add("C#m");
                chords.add("D#m");
                chords.add("E");
                chords.add("F#7");
                chords.add("G#m");
                chords.add("A#o");
                break;

            case "F#":
                chords.add("F#");
                chords.add("G#m");
                chords.add("A#m");
                chords.add("B");
                chords.add("C#7");
                chords.add("D#m");
                chords.add("Fo");
                break;

            case "Db":
                chords.add("E");
                chords.add("F#m");
                chords.add("G#m");
                chords.add("A");
                chords.add("B7");
                chords.add("C#m");
                chords.add("D#o");
                break;

            case "Ab":
                chords.add("E");
                chords.add("F#m");
                chords.add("G#m");
                chords.add("A");
                chords.add("B7");
                chords.add("C#m");
                chords.add("D#o");
                break;

            case "Eb":
                chords.add("Eb");
                chords.add("Fm");
                chords.add("Gm");
                chords.add("Ab");
                chords.add("Bb7");
                chords.add("Cm");
                chords.add("Eo");
                break;

            case "Bb":
                chords.add("Bb");
                chords.add("Cm");
                chords.add("Dm");
                chords.add("Eb");
                chords.add("F7");
                chords.add("Gm");
                chords.add("Ao");
                break;

            case "F":
                chords.add("F");
                chords.add("Gm");
                chords.add("Am");
                chords.add("Bb");
                chords.add("C7");
                chords.add("Dm");
                chords.add("Eo");
                break;

            default:
                chords.add("C");
                chords.add("G");
                chords.add("D");
                break;

        }
        return chords;
    }
}
