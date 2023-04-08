package com.walletmusic.dao.impl;

import com.walletmusic.dao.IGenresDAO;
import com.walletmusic.mapper.GenresMapper;
import com.walletmusic.model.GenresModel;

import java.util.List;

public class GenresDAO extends AbstractDAO<GenresModel> implements IGenresDAO {
    @Override
    public List<GenresModel> findAll() {
        String sql = "SELECT * from genres";
        return query(sql,new GenresMapper());
    }

    @Override
    public GenresModel findOne(int id) {
        String sql = "SELECT * from genres WHERE id = ?";
        List<GenresModel> genres = query(sql,new GenresMapper(), id);
        return genres.isEmpty() ? null : genres.get(0);
    }
}
