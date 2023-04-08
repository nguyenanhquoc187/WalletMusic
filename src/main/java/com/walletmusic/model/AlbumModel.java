package com.walletmusic.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AlbumModel extends AbstractModel<AlbumModel> {
    private String name;
    private String image;
    private Date releaseDate;
    private int genresId;
    private int totalSong;
    private List<Integer> songIdList = new ArrayList<>();
    private List<SongModel> songList = new ArrayList<>();
    private List<Integer> artistIdList = new ArrayList<>();

    public List<Integer> getSongIdList() {
        return songIdList;
    }

    public void setSongIdList(List<Integer> songIdList) {
        this.songIdList = songIdList;
    }

    public List<SongModel> getSongList() {
        return songList;
    }

    public void setSongList(List<SongModel> songList) {
        this.songList = songList;
    }

    public List<Integer> getArtistIdList() {
        return artistIdList;
    }

    public void setArtistIdList(List<Integer> artistIdList) {
        this.artistIdList = artistIdList;
    }

    public int getTotalSong() {
        return totalSong;
    }

    public void setTotalSong(int totalSong) {
        this.totalSong = totalSong;
    }

    public int getGenresId() {
        return genresId;
    }

    public void setGenresId(int genresId) {
        this.genresId = genresId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
