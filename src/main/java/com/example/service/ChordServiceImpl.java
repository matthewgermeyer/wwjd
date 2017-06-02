package com.example.service;

import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
@Service
public class ChordServiceImpl implements ChordService {

    @Override
    public List<String> getBasicChords(String key) {
        List<String> chords = new ArrayList<>();
        chords.add("C");
        chords.add("Am");
        chords.add("Em");
        chords.add("G");
        chords.add("D");
        return chords;
    }

    @Override
    public List<String> addSuggestedChord(String key, List<String> songChords) {
        List<String> chords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I");
        cp.setKey(key);

        for (Chord chord : cp.getChords()) {
            System.out.println("=============");
            System.out.println(chord);
            System.out.println("chordType -> " + chord.getChordType());
            chords.add("Human readable ---> " + chord.toHumanReadableString());

            String chordAsString = chord.toHumanReadableString().substring(0, 1);
            if (chord.isMajor()) {
                chordAsString += "M";
            } else if (chord.isMinor()) {
                chordAsString += "m";
            }
            chords.add(chordAsString);
        }
        return chords;
    }

    @Override
    public List<String> jimiPick(List<String> songChords) {
        System.out.println("entered jimiPick");
        System.out.println("songChords prior = " + songChords);
        System.out.println("*************");
        System.out.println("Last Chord is" + songChords.get(songChords.size()-1));

        //if Last Chord is Am add Bb7o to songChords
        if (songChords.get(0) != null){
            System.out.println("***#*#*$@(#$*&(@#*&$(@*#&$");
            System.out.println("tell me something good! " + songChords.get(0));

        }
        if (songChords.get(songChords.size() - 1).equals("Am")) {
            songChords.add("Bb7o");
        } else {
            System.out.println(songChords);
            System.out.println("adding C");
            songChords.add("C");
            System.out.println(songChords);
            System.out.println("Size of songChords is " + songChords.size());
        }
        System.out.println("==============");
        System.out.println(songChords);

        //picker is random 1-10... 1-5 up a 4th, 6-10 up a 5th
        //given that chord's root ->  choose the root for the candidate chord
        // choose an extension (major / minor for the candidate chord)
        // songChords.add(int index, E element)
        return songChords;
    }

}



