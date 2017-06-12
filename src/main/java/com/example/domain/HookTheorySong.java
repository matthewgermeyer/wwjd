package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MattyG on 6/11/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HookTheorySong {

    String artist;
    String song;
    String section;
    String url;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                "artist='" + artist + '\'' +
                ", song='" + song + '\'' +
                ", section='" + section + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
