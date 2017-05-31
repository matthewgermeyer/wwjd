package com.example.service;

import domain.Chord;

import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
public interface ChordService {

    //Get suggested chords given the key and the song as it currently exists.
    List<String> getSuggestedChords(String key, List<String> songChords);


    //Add a selected chord to end of chart.
    void addChord();

    //Remove chord from end of chart.
    void deleteChord();

    //Get the list of chords in a song.
    List<Chord> listChordsForSong();


}
