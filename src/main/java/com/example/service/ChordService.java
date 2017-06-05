package com.example.service;

import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
public interface ChordService {

     List<String> getBasicChords(String key);
     List<String> addSuggestedChord(String key, List<String> songChords);
     List<String> jimiPickNextChord(List<String> songChords);
     String getNextChord(String lastChord);
     String getNextExt();

     List<String> generateProgression_1454(String key);
     List<String> generateProgression_1564(String key);
     List<String> generateProgression_1645(String key);
     List<String> generateProgression_12bar(String key);


}
