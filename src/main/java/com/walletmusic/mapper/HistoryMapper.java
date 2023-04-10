package com.walletmusic.mapper;

import com.walletmusic.model.HistoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryMapper implements RowMapper<HistoryModel> {
    @Override
    public HistoryModel mapRow(ResultSet resultSet) {
        HistoryModel historyModel = new HistoryModel();
        try {
            historyModel.setId((long) resultSet.getInt("id"));
            historyModel.setSongId(resultSet.getInt("song_id"));
            historyModel.setUserId(resultSet.getInt("user_id"));
            historyModel.setDate(resultSet.getTimestamp("date"));
            return historyModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
