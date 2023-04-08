package com.walletmusic.dao;

import com.walletmusic.model.GenresModel;

import java.util.List;

public interface IGenresDAO extends GenericDAO<GenresModel> {
    List<GenresModel> findAll();
    GenresModel findOne(int id);
}
