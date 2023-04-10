package com.walletmusic.model;

import java.sql.Date;
import java.sql.Timestamp;

public class HistoryModel {
    private Long id;
    private Timestamp date;
    private int songId;
    private SongModel song = new SongModel();
    private int userId;

    public SongModel getSong() {
        return song;
    }

    public void setSong(SongModel song) {
        this.song = song;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
