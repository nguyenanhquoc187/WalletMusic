package com.walletmusic.model;

import java.util.ArrayList;
import java.util.List;

public class SongModel extends AbstractModel<SongModel>{
    private String title;
    private String lyrics;
    private String image;
    private String mediaUrl;
    private Long countListen;
    private String timePlay;
    private int albumId;
    private ArrayList<Integer> genresIdList = new ArrayList<>();
    private ArrayList<Integer> artistIdList = new ArrayList<>();
    private AlbumModel album = new AlbumModel();
    private ArtistModel artist = new ArtistModel();
    private List<ArtistModel> artistList = new ArrayList<>();
    private GenresModel genres = new GenresModel();
    private List<GenresModel> genresList = new ArrayList<>();

    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel artist) {
        this.artist = artist;
    }

    public List<ArtistModel> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<ArtistModel> artistList) {
        this.artistList = artistList;
    }

    public GenresModel getGenres() {
        return genres;
    }

    public void setGenres(GenresModel genres) {
        this.genres = genres;
    }

    public List<GenresModel> getGenresList() {
        return genresList;
    }

    public void setGenresList(List<GenresModel> genresList) {
        this.genresList = genresList;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel album) {
        this.album = album;
    }



    public ArrayList<Integer> getGenresIdList() {
        return genresIdList;
    }

    public void setGenresIdList(ArrayList<Integer> genresIdList) {
        this.genresIdList = genresIdList;
    }

    public ArrayList<Integer> getArtistIdList() {
        return artistIdList;
    }

    public void setArtistIdList(ArrayList<Integer> artistIdList) {
        this.artistIdList = artistIdList;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Long getCountListen() {
        return countListen;
    }

    public void setCountListen(Long countListen) {
        this.countListen = countListen;
    }

    public String getTimePlay() {
        return timePlay;
    }

    public void setTimePlay(String timePlay) {
        this.timePlay = timePlay;
    }
}
