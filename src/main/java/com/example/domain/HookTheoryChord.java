package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MattyG on 7/6/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HookTheoryChord {
    String chord_ID;
    String chord_HTML;
    Double probability;

    public String getChord_ID() {
        return chord_ID;
    }

    public void setChord_ID(String chord_ID) {
        this.chord_ID = chord_ID;
    }

    public String getChord_HTML() {
        return chord_HTML;
    }

    public void setChord_HTML(String chord_HTML) {
        this.chord_HTML = chord_HTML;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "HookTheoryChord{" +
                "chord_ID='" + chord_ID + '\'' +
                ", chord_HTML='" + chord_HTML + '\'' +
                ", probability=" + probability +
                '}';
    }
}
