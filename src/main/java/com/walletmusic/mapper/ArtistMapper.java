package com.walletmusic.mapper;

import com.walletmusic.model.ArtistModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistMapper implements RowMapper<ArtistModel> {

    @Override
    public ArtistModel mapRow(ResultSet resultSet) {
        ArtistModel artist = new ArtistModel();
        try {
            artist.setId(resultSet.getInt("id"));
            artist.setName(resultSet.getString("name"));
            artist.setBirthDay(resultSet.getDate("birth_day"));
            artist.setGender(resultSet.getString("gender"));
            artist.setImage(resultSet.getString("image"));
            try {
                artist.setTotalListen(resultSet.getInt("total_listen"));
            } catch (SQLException e) {
                e.getMessage();
            }
            return artist;
        } catch (SQLException e) {
            return null;
        }
    }
}
