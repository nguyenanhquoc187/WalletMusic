package com.walletmusic.mapper;

import com.walletmusic.model.GenresModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresMapper implements RowMapper<GenresModel> {
    @Override
    public GenresModel mapRow(ResultSet resultSet) {
        GenresModel genre = new GenresModel();
        try {
            genre.setId(resultSet.getInt("id"));
            genre.setName(resultSet.getString("name"));
            return genre;
        } catch (SQLException e) {
            return null;
        }
    }
}
