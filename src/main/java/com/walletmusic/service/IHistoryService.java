package com.walletmusic.service;

import com.walletmusic.model.HistoryModel;

import java.util.List;

public interface IHistoryService {
    int save(HistoryModel historyModel);
    List<HistoryModel> findAllHistoryByUser(int userId);
}
