package com.example.service;

import com.example.domain.HookTheorySong;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by MattyG on 6/12/17.
 */
@Service
public class HookTheoryServiceImpl implements HookTheoryService{

    @Override
    public List<HookTheorySong> getHookTheorySongs(String genre) {

        String targetURL ="https://api.hooktheory.com/v1/trends/songs?cp=";
        String genreURL;

        switch (genre){
            case "blues":
                genreURL ="1,4,5";
                break;
            case "popRock":
                genreURL ="1,5,6,4";
                break;
            case "soulful":
                genreURL ="1,6,4,5";
                break;
            case "classicRock":
                genreURL ="1,4,5,4";
                break;
            default:
                genreURL = "1";
        }
        String finishedURL = targetURL + genreURL;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer b3ebb8456869f5fcac5255dfd06b8063");
        headers.set("Accept", "application/json");
        headers.set("Content", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        System.out.println(restTemplate.exchange(finishedURL, HttpMethod.GET, entity, String.class));

        ResponseEntity<List<HookTheorySong>> songResponse =
                restTemplate.exchange(finishedURL,
                        HttpMethod.GET, entity, new ParameterizedTypeReference<List<HookTheorySong>>() {
                        });
        List<HookTheorySong> songs = songResponse.getBody();

        for (HookTheorySong song : songs) {
            System.out.println(song);
        }

        return songs;
    }

}
