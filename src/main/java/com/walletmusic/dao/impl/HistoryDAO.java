package com.walletmusic.dao.impl;

import com.walletmusic.dao.IHistoryDAO;
import com.walletmusic.dao.ISongDAO;
import com.walletmusic.mapper.HistoryMapper;
import com.walletmusic.model.HistoryModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.sort.Sorter;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class HistoryDAO extends AbstractDAO<HistoryModel> implements IHistoryDAO {
    @Inject
    private ISongDAO songDAO;
    @Override
    public int save(HistoryModel historyModel) {
        historyModel.setDate(new Timestamp(System.currentTimeMillis()));
        String sql = "INSERT INTO history_listens(date,song_id,user_id) VALUES(?, ?, ?)";
        songDAO.updateCountlisten(historyModel.getSongId());
        int id = insert(sql,historyModel.getDate(),historyModel.getSongId(),historyModel.getUserId());
        return id;
    }

    @Override
    public List<HistoryModel> findAllHistoryByUser(int userId) {
        String sql = "SELECT id, max(date) as date, song_id, user_id FROM history_listens where user_id = ? " +
                "group by song_id order by date desc limit 10";
        List<HistoryModel> historyModels = query(sql,new HistoryMapper(),userId);
        for (HistoryModel history : historyModels) {
            history.setSong(songDAO.findOneWithArtistAndAlbum(history.getSongId()));
        }
        return historyModels;
    }
}
