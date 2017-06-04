package com.example.service;

import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by MattyG on 5/31/17.
 */
@Service
public class ChordServiceImpl implements ChordService {
    static Random random = new Random();

    @Override
    public List<String> getBasicChords(String key) {
        List<String> chords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I ii iii IV V vi ");
        cp.setKey(key);

        System.out.println("================================");
        System.out.println("making basic chord palette for "+ key +".  Progression ->" + cp);


        //iterate through jfugue generic progression.
        //Apply our key.
        //return basic chord list from the results.
        for (Chord chord : cp.getChords()) {
            System.out.println("================================");
            System.out.println("chord original -> " + chord.toHumanReadableString());

            String original = chord.toHumanReadableString();
            String root = original.substring(0,1);
            String ext = original.toLowerCase().substring(2);

            switch (ext){

                case ("maj"):
                    ext = "";
                    break;

                case ("min"):
                    ext = "m";
                    break;
                default: ext = "";
            }
            //Assemble the finished chords and add them to chords List.
            String finishedChord = root + ext;
            chords.add(finishedChord);
            System.out.println("Root: " + root + "   ||  Ext: " + ext);
            System.out.println("Finished -- " + finishedChord);
            System.out.println("chords -> " + chords);
        }
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

        }
        return chords;
    }

    @Override
    public List<String> jimiPick(List<String> songChords) {

        String lastChord = songChords.get(songChords.size() - 1);
        String lastChordRoot = lastChord.substring(0, 1);
        String lastChordExt = lastChord.substring(1);
        String defaultChord = "C";

        System.out.println("entered jimiPick");
        System.out.println("songChords prior = " + songChords);
        System.out.println("*************");
        System.out.println("Last Chord is " + lastChord);

        //if Last Chord is Am add E7 to songChords
        if (lastChord.equals("Am")) {
            System.out.println("Root is -> " + lastChordRoot);
            System.out.println("extension is -> " + lastChordExt);
            songChords.add("E7");
        } else {
            //Add C instead
            System.out.println(songChords);
            System.out.println("adding default chord" + defaultChord);
            System.out.println(songChords);
            System.out.println("Size of songChords is " + songChords.size());

            songChords.add(getNextChord(lastChord));
            System.out.println(songChords);

        }
        System.out.println("==============");
        System.out.println(songChords);

        return songChords;
    }

    public String getNextChord(String lastChord) {
        String nextChord;

        String nextChordRoot = getNextRoot(lastChord);
        String nextChordExt = getNextExt();

        nextChord = nextChordRoot + nextChordExt;

        return nextChord;

    }

    public String getNextRoot(String lastChord) {
        String nextRoot;
        String lastRoot = lastChord.substring(0, 1);

        int picker = random.nextInt(4);
        System.out.println("picker is " + picker);

        switch (picker) {
            case 0:
                nextRoot = lastRoot;
                break;

            case 1:
                nextRoot = "C";
                break;
            case 2:
                nextRoot = "D";
                break;
            case 3:
                nextRoot = "G";
                break;
            case 4:
                nextRoot = "A";
                break;

            default:
                nextRoot = "Q";
        }

        return nextRoot;
    }

    public String getNextExt() {
        System.out.println("========================");
        System.out.println(" Entering get Next ext");
        String nextExt;

        int picker = random.nextInt(4);
        System.out.println("********Picker is*******" + picker);
        switch (picker) {
            case 0:
                nextExt = "";
                break;
            case 1:
                nextExt = "m";
                break;
            case 2:
                nextExt = "7";
                break;
            case 3:
                nextExt = "m7";
                break;
            default:
                nextExt = "";

        }
        return nextExt;
    }


}



