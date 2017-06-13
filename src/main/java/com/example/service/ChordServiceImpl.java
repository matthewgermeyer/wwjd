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
        ChordProgression cp = new ChordProgression("I ii iii IV V vi vii");
        cp.setKey(key);
        return chordsFromCP(cp);
    }

    public List<String> generateSimpleRock(String key) {
        ChordProgression cp = new ChordProgression("I IV V IV I IV V IV");
        cp.setKey(key);

        return chordsFromCP(cp);
    }

    public List<String> generatePopRock(String key) {
        ChordProgression cp = new ChordProgression("I V vi IV I V vi IV");
        cp.setKey(key);
        return chordsFromCP(cp);

    }

    public List<String> generateSoulful(String key) {
        ChordProgression cp = new ChordProgression("I vi IV V I vi IV V");
        cp.setKey(key);
        return chordsFromCP(cp);
    }

    public List<String> generateBlues(String key) {
        ChordProgression cp = new ChordProgression("I I I I IV IV I I V IV I IV");
        cp.setKey(key);
        return chordsFromCP(cp);
    }

    private List<String> chordsFromCP(ChordProgression cp) {
        List<String> chords = new ArrayList<>();

        //Use jFugue to convert our generic progression to human readable chords list
        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            //First gather the root
            //Drop off the number (3, 4 or 5) that represents the octave of the root.
            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            //Gather the extension
            String ext = original.substring(original.length() - 3);
            ext = ext.toLowerCase();

            //convert the extension to more user friendly versions.
            switch (ext) {

                case ("maj"):
                    ext = "";
                    break;

                case ("min"):
                    ext = "m";
                    break;
                default:
                    ext = "";
            }
            String finishedChord = root + ext;
            chords.add(finishedChord);

        }
        return chords;

    }

}



