package com.walletmusic.service.impl;

import com.walletmusic.dao.IGenresDAO;
import com.walletmusic.model.GenresModel;
import com.walletmusic.service.IGenresService;

import javax.inject.Inject;
import java.util.List;

public class GenresService implements IGenresService {
    @Inject
    private IGenresDAO genresDao;
    @Override
    public List<GenresModel> findAll() {
        return genresDao.findAll();
    }
}
