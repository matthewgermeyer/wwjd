package com.example.service;

import com.example.domain.HookTheorySong;

import java.util.List;

/**
 * Created by MattyG on 6/12/17.
 */
public interface HookTheoryService {

     List<HookTheorySong> getHookTheorySongs(String genre);
}
