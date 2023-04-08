package com.walletmusic.service;

import com.walletmusic.model.GenresModel;

import java.util.List;

public interface IGenresService {
    List<GenresModel> findAll();
}
