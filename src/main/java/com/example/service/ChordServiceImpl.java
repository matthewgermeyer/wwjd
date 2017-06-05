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
        System.out.println("getBasicChords in key -> " + key);

        //Use jFugue to convert our generic progression to human readable chords list
        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            //First gather and clean up the root
            //Drop off the number (3, 4 or 5) that represents the octave of the root.
            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            //Gather the root, ensure it's lowercase
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
            //Assemble the finished chord
            String finishedChord = root + ext;
            //Add it to the list
            chords.add(finishedChord);

        }
        System.out.println("chords -> " + chords);
        System.out.println("=============================");

        //1 4 5
        System.out.println("12 bar blues");
        System.out.println(generateProgression_12bar(key)+ "\n");

        //1 4 5 4
        System.out.println("Pop Rock I IV V IV | e.g. With or Without You, Under the Bridge ");
        System.out.println(generateProgression_1454(key) + "\n");

        // 1 5 6 4
        System.out.println("I V vi IV | e.g.  Let it Be/ Beast of Burden");
        System.out.println(generateProgression_1564(key)+ "\n");

        //1 6 4 5
        System.out.println("I vi IV V |  e.g. Stand by Me");
        System.out.println(generateProgression_1645(key)+ "\n");



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
        }
        return chords;
    }

    @Override
    public List<String> jimiPickNextChord(List<String> songChords) {
        System.out.println("====================");
        System.out.println("entered jimiPickNextChord");

        if (songChords.size() < 1) {
            songChords.add("C");
        }

        String lastChord = songChords.get(songChords.size() - 1);
        String lastChordRoot = lastChord.substring(0, 1);
        String lastChordExt = lastChord.substring(1);

        System.out.println("songChords prior = " + songChords);

        //if Last Chord is Am add E7 to songChords
        if (lastChord.equals("Am")) {
            System.out.println("Root is -> " + lastChordRoot);
            System.out.println("extension is -> " + lastChordExt);
            songChords.add("Dm");

        } else {
            songChords.add(getNextChord(lastChord));

        }
        System.out.println("==============");
        System.out.println(songChords);

        return songChords;
    }

    public String getNextChord(String lastChord) {
        String nextChord;
        nextChord = getNextRoot(lastChord) + getNextExt();
        return nextChord;

    }

    public String getNextRoot(String lastChord) {
        String nextRoot;
        String lastRoot = lastChord.substring(0, 1);

        int picker = random.nextInt(7);

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
            case 7:
                nextRoot = "B";
                break;

            default:
                nextRoot = "Q";
        }
        return nextRoot;
    }

    public String getNextExt() {
        String nextExt;

        int picker = random.nextInt(4);

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


    public List<String> generateProgression_1454(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I IV V IV");
        cp.setKey(key);

        //Use jFugue to convert our generic progression to human readable chords list
        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            //First gather and clean up the root
            //Drop off the number (3, 4 or 5) that represents the octave of the root.
            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            //Gather the root, ensure it's lowercase
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
            //Assemble the finished chord
            String finishedChord = root + ext;
            //Add it to the list
            songChords.add(finishedChord);

        }
        return songChords;
    }

    public List<String> generateProgression_1564(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I V vi IV");
        cp.setKey(key);

        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            String ext = original.substring(original.length() - 3);
            ext = ext.toLowerCase();

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
            songChords.add(finishedChord);

        }
        return songChords;


    }

    public List<String> generateProgression_1645(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I V vi IV");
        cp.setKey(key);

        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            String ext = original.substring(original.length() - 3);
            ext = ext.toLowerCase();

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
            songChords.add(finishedChord);

        }
        return songChords;
    }
    public List<String> generateProgression_12bar(String key){
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I I I I IV IV I I V IV I V").distribute("7%6");
        cp.setKey(key);

        for (Chord chord : cp.getChords()) {
            String original = chord.toHumanReadableString();
            String root = original.substring(0, 2);

            if (root.substring(1, 2).equals("4") || root.substring(1, 2).equals("5") || root.substring(1, 2).equals("3")) {
                root = root.substring(0, 1);
            }
            String ext = original.substring(original.length() - 3);
            ext = ext.toLowerCase();

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
            songChords.add(finishedChord);

        }
        return songChords;
    }


}



