package com.example.service;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
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

        System.out.println("======Get Basic Chords========");
        System.out.println("getBasicChords in key -> " + key);

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
        System.out.println("chords -> " + chords);
        System.out.println("==============");

        //blues
        System.out.println("12 bar blues");
        System.out.println(generateBlues(key)+ "\n");

        //simple rock
        System.out.println("Simple Rock I IV V IV | e.g. With or Without You, Under the Bridge ");
        System.out.println(generateSimpleRock(key) + "\n");

        // Pop Rock
        System.out.println("I V vi IV | e.g.  Let it Be/ Beast of Burden");
        System.out.println(generatePopRock(key)+ "\n");

        //Soulful
        System.out.println("I vi IV V |  e.g. Stand by Me");
        System.out.println(generateSoulful(key)+ "\n");

        return chords;
    }

    @Override
    public List<String> addSuggestedChord(String key, List<String> songChords) {
        System.out.println("=============");
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

        //if Last Chord is Am add Dm to songChords
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
                //This is bad!
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

    public List<String> generateSimpleRock(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I IV V IV I IV V IV");
        cp.setKey(key);

        Pattern simpleRock = new Pattern(cp);
        try {
            MidiFileManager
                    .savePatternToMidi(simpleRock, new File("simpleRock.mid"));
        } catch (IOException ex) {
            System.out.println("IO exception whilst saving!");
        }
        ConvertFile("simpleRock.mid", "simpleRock.wav");

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

    public List<String> generatePopRock(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I V vi IV I V vi IV");
        cp.setKey(key);

        Pattern popRock = new Pattern(cp);
        try {
            MidiFileManager
                    .savePatternToMidi(popRock, new File("popRock.mid"));
        } catch (IOException ex) {
            System.out.println("IO exception whilst saving!");
        }
        ConvertFile("popRock.mid", "popRock.wav");



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

    public List<String> generateSoulful(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I vi IV V I vi IV V");
        cp.setKey(key);

        Pattern soulful = new Pattern(cp);
        try {
            MidiFileManager
                    .savePatternToMidi(soulful, new File("soulful.mid"));
        } catch (IOException ex) {
            System.out.println("IO exception whilst saving!");
        }
        ConvertFile("soulful.mid", "soulful.wav");

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
    public List<String> generateBlues(String key) {
        List<String> songChords = new ArrayList<>();
        ChordProgression cp = new ChordProgression("I I I I IV IV I I V IV I IV");
        cp.setKey(key);

        Pattern blues = new Pattern(cp);
        try {
            MidiFileManager
                    .savePatternToMidi(blues, new File("blues.mid"));
        } catch (IOException ex) {
            System.out.println("IO exception whilst saving!");
        }
        System.out.println("===============");
        System.out.println("calling convertFile");
        ConvertFile("blues.mid", "blues.wav");

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
            String finishedChord = root + ext + "7";
            songChords.add(finishedChord);

        }
        return songChords;
    }

    //Convert .mid -> .wav for playback
    public static void ConvertFile(String inputPath,
                                   String outputPath) {

        AudioFileFormat inFileFormat;
        File inFile;
        File outFile;
        try {
            inFile = new File( inputPath);
            outFile = new File("target/classes/static/" + outputPath);
        } catch (NullPointerException ex) {
            System.out.println("Error: one of the ConvertFile to wav" +" parameters is null!");
            return;
        }
        try {
            // query file type
            inFileFormat = AudioSystem.getAudioFileFormat(inFile);
            if (inFileFormat.getType() != AudioFileFormat.Type.WAVE)
            {
                // inFile is not wav, so let's try to convert it.
                AudioInputStream inFileAIS =
                        AudioSystem.getAudioInputStream(inFile);
                //inFileAIS.reset(); // rewind
                if (AudioSystem.isFileTypeSupported(
                        AudioFileFormat.Type.WAVE, inFileAIS)) {
                    // inFileAIS can be converted to wav.
                    // so write the AudioInputStream to the
                    // output file.
                    AudioSystem.write(inFileAIS,
                            AudioFileFormat.Type.WAVE, outFile);
                    System.out.println("Successfully made wav file, "
                            + outFile.getPath() + ", from "
                            + inFileFormat.getType() + " file, " +
                            inFile.getPath() + ".");
                    inFileAIS.close();
                    return; // All done now
                } else
                    System.out.println("Warning: wav conversion of "
                            + inFile.getPath()
                            + " is not currently supported by AudioSystem.");
            } else
                System.out.println("Input file " + inFile.getPath() +
                        " is wav." + " Conversion is unnecessary.");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: " + inFile.getPath()
                    + " is not a supported audio file type!");
            return;
        } catch (IOException e) {
            System.out.println("Error: failure attempting to read "
                    + inFile.getPath() + "!");
            e.printStackTrace();
            return;
        }
    }

}



