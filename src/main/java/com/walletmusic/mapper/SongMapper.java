package com.walletmusic.mapper;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongMapper implements RowMapper<SongModel> {

    @Override
    public SongModel mapRow(ResultSet resultSet) {
        SongModel song = new SongModel();
        try {
            if ( !resultSet.wasNull() )  song.setAlbumId(resultSet.getInt("album_id"));
            song.setId(resultSet.getInt("id"));
            song.setTitle(resultSet.getString("title"));
            song.setLyrics(resultSet.getString("lyrics"));
            song.setImage(resultSet.getString("song_img"));
            song.setMediaUrl(resultSet.getString("media_url"));
            song.setTimePlay(resultSet.getString("time_play"));
            song.setCountListen(resultSet.getLong("count_listen"));
//            song.getArtistIdList().add(resultSet.getInt("artist_id"));
            try {
                String listArtistId = resultSet.getString("artist_id_list");
                for (String i : listArtistId.split(",")) {
                    song.getArtistIdList().add(Integer.parseInt(i));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                ArtistModel artist = new ArtistModel();
                artist.setId(resultSet.getInt("artist_id"));
                artist.setName(resultSet.getString("artist_name"));
                artist.setBirthDay(resultSet.getDate("birth_day"));
                artist.setGender(resultSet.getString("gender"));
                artist.setImage(resultSet.getString("artist_img"));
                song.setArtist(artist);
                song.getArtistList().add(artist);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            try {
                AlbumModel album = new AlbumModel();
                album.setName(resultSet.getString("album_name"));
                album.setImage(resultSet.getString("album_img"));
                album.setReleaseDate(resultSet.getDate("release_date"));
                album.setGenresId(resultSet.getInt("genre_id"));
                song.setAlbum(album);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return song;
        } catch (SQLException e) {
            return song;
        }
    }
}
