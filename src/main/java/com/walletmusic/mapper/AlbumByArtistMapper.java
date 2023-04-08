package com.walletmusic.mapper;

import com.walletmusic.model.AlbumModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumByArtistMapper implements RowMapper<AlbumModel> {
    @Override
    public AlbumModel mapRow(ResultSet resultSet) {
        AlbumModel album = new AlbumModel();
        try {
            album.setId(resultSet.getInt("album_id"));
            String listArtistId = resultSet.getString("artistIdList");
            for (String i : listArtistId.split(",")) {
                album.getArtistIdList().add(Integer.parseInt(i));
            }
            return album;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
