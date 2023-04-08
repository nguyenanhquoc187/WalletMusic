package com.walletmusic.mapper;

import com.walletmusic.model.AlbumModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper implements RowMapper<AlbumModel> {
    @Override
    public AlbumModel mapRow(ResultSet resultSet) {
        AlbumModel album = new AlbumModel();
        try {
            album.setId(resultSet.getInt("id"));
            album.setName(resultSet.getString("name"));
            album.setImage(resultSet.getString("image"));
            album.setReleaseDate(resultSet.getDate("release_date"));
            return album;
        } catch (SQLException e) {
            return null;
        }

    }
}
