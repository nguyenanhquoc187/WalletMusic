package com.walletmusic.service.impl;

import com.walletmusic.dao.IHistoryDAO;
import com.walletmusic.model.HistoryModel;
import com.walletmusic.service.IHistoryService;

import javax.inject.Inject;
import java.util.List;

public class HistoryService implements IHistoryService {
    @Inject
    private IHistoryDAO historyDAO;
    @Override
    public int save(HistoryModel historyModel) {
        return historyDAO.save(historyModel);
    }

    @Override
    public List<HistoryModel> findAllHistoryByUser(int userId) {
        return historyDAO.findAllHistoryByUser(userId);
    }

}
