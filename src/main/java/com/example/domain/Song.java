package com.example.domain;

import java.util.List;

/**
 * Created by MattyG on 6/8/17.
 */
public class Song {
    private int id;
    private String title;
    private String key;
    private String genre;
    private String username;
    private List<String> chords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getChords() {
        return chords;
    }

    public void setChords(List<String> chords) {
        this.chords = chords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (key != null ? !key.equals(song.key) : song.key != null) return false;
        return genre != null ? genre.equals(song.genre) : song.genre == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", genre='" + genre + '\'' +
                ", username='" + username + '\'' +
                ", chords=" + chords +
                '}';
    }
}
