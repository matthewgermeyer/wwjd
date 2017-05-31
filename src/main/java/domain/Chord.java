package domain;

/**
 * Created by MattyG on 5/31/17.
 */
public class Chord {
    int id;
    String name;
    String roman;

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

    public String getRoman() {
        return roman;
    }

    public void setRoman(String roman) {
        this.roman = roman;
    }

    @Override
    public String toString() {
        return "Chord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roman='" + roman + '\'' +
                '}';
    }
}
