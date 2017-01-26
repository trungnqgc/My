package com.example.merrychistmasnguyenquangtrung2016.mymp3.model;

/**
 * Created by dell on 1/16/2017.
 */

public class SongModel {
    private String title;
    private String casy;
    private int path;
    private boolean favotite;

    public SongModel(String title, String casy, int path, boolean favotite) {
        this.title = title;
        this.casy = casy;
        this.path = path;
        this.favotite = favotite;
    }

    public SongModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getCasy() {
        return casy;
    }

    public int getPath() {
        return path;
    }

    public boolean isFavotite() {
        return favotite;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCasy(String casy) {
        this.casy = casy;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public void setFavotite(boolean favotite) {
        this.favotite = favotite;
    }
}
