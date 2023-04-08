package com.walletmusic.mapper;

import com.walletmusic.model.SongModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongByGenresMapper implements RowMapper<SongModel> {
    @Override
    public SongModel mapRow(ResultSet resultSet) {
        SongModel song = new SongModel();
        try {
            song.setId(resultSet.getInt("song_id"));
            String listGenresId = resultSet.getString("genresIdList");
            for (String i : listGenresId.split(",")) {
                song.getGenresIdList().add(Integer.parseInt(i));
            }
            return song;
        } catch (SQLException e) {
            return null;
        }
    }
//    chỉ get thể loại
}
