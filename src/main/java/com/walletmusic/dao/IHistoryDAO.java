package com.walletmusic.dao;

import com.walletmusic.model.HistoryModel;

import java.util.List;

public interface IHistoryDAO extends GenericDAO<HistoryModel>{
    int save(HistoryModel historyModel);
    List<HistoryModel> findAllHistoryByUser(int userId);
}
