package com.example.service;

import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
public interface ChordService {

    List<String> getBasicChords(String key);

    //Get Dominant chords that make sense given the key.
    public List<String> addSuggestedChord(String key, List<String> songChords);

    public List<String> jimiPick(List<String> songChords);

    public String getNextChord(String lastChord);
    public String getNextRoot(String lastChord);
    public String getNextExt();


}
