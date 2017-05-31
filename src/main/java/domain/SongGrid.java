package domain;

import java.util.List;

/**
 * Created by MattyG on 5/31/17.
 */
public class SongGrid {

    int id;
    String name;
    List<Chord> chords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Chord> getChords() {
        return chords;
    }

    public void setChords(List<Chord> chords) {
        this.chords = chords;
    }

    @Override
    public String toString() {
        return "SongGrid{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chords=" + chords +
                '}';
    }
}
