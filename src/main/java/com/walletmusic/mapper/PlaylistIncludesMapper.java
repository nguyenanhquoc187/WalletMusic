package com.walletmusic.mapper;

import com.walletmusic.model.SongModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistIncludesMapper implements RowMapper<SongModel> {
    @Override
    public SongModel mapRow(ResultSet resultSet) {
        SongModel song = new SongModel();
        try {
            song.setId(resultSet.getInt("song_id"));
            return song;
        } catch (SQLException e) {
            return null;
        }
    }
}
