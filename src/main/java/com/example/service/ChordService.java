package com.example.service;

import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 * Just a comment
 */
public interface ChordService {

     List<String> getBasicChords(String key);
     List<String> addSuggestedChord(String key, List<String> songChords);
     List<String> jimiPickNextChord(List<String> songChords);
     String getNextChord(String lastChord);
     String getNextExt();

     List<String> generateSimpleRock(String key);
     List<String> generatePopRock(String key);
     List<String> generateSoulful(String key);
     List<String> generateBlues(String key);


}
