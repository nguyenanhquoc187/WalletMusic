package com.walletmusic.mapper;

import com.walletmusic.model.PlaylistModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMapper implements RowMapper<PlaylistModel> {

    @Override
    public PlaylistModel mapRow(ResultSet resultSet) {
        PlaylistModel playlist = new PlaylistModel();
        try {
            playlist.setId(resultSet.getInt("id"));
            playlist.setName(resultSet.getString("name"));
            playlist.setImage(resultSet.getString("image"));
            playlist.setUserId(resultSet.getInt("user_id"));
            return playlist;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
