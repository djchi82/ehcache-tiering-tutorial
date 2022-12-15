package com.tenmilesquare.ryan.ehcache;

import java.io.Serializable;

/**
 * To use non-heap cache options and a POJO we need to implement the serializable interface
 * https://www.ehcache.org/documentation/3.1/serializers-copiers.html
 */
public class Music implements Serializable {

    private String title;
    private String artist;
    private String label;
    private String id;

    public Music() {
        super();
    }

    public Music(String id, String title, String artist, String label) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.label = label;
    }

    @Override
    public String toString() {
        return "Music{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", label='" + label + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
