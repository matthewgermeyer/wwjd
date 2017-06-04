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

        System.out.println("==============Get Basic Chords==================");
        System.out.println("key is -> " + key);
        System.out.println("Populating pick list using getBasicChords.  Chords ->" + cp);

        //Turn jfugue progression into our chord list

        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();


            String root = original.substring(0,2);
            if (root.substring(1,2).equals("4") || root.substring(1,2).equals("5")){
                root = root.substring(0,1);
            }
            
            String ext = original.substring(original.length() - 3);
            ext = ext.toLowerCase();

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
            System.out.println("************** Roots" + root);

            String finishedChord = root + ext;
//            if (finishedChord.substring(1,2).equals("B"))
            chords.add(finishedChord);

        }
        System.out.println("chords -> " + chords);
        return chords;
    }

    @Override
    public List<String> addSuggestedChord(String key, List<String> songChords) {
        System.out.println("=============");
        System.out.println("entering addSuggestedChord");
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
        System.out.println("====================");
        System.out.println("entered jimiPick");

        String lastChord = songChords.get(songChords.size() - 1);
        String lastChordRoot = lastChord.substring(0, 1);
        String lastChordExt = lastChord.substring(1);

        System.out.println("songChords prior = " + songChords);

        //if Last Chord is Am add E7 to songChords
        if (lastChord.equals("Am")) {
            System.out.println("Root is -> " + lastChordRoot);
            System.out.println("extension is -> " + lastChordExt);
            songChords.add("E7");

        }  else {

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

        int picker = random.nextInt(6);
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
                nextRoot = "E";
                break;
            case 4:
                nextRoot = "F";
                break;
            case 5:
                nextRoot = "G";
                break;
            case 6:
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



