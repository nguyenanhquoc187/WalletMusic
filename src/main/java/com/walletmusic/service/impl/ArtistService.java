package com.walletmusic.service.impl;

import com.walletmusic.dao.IArtistDAO;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IArtistService;

import javax.inject.Inject;
import java.util.List;

public class ArtistService implements IArtistService {
    @Inject
    private IArtistDAO artistDao;
    @Override
    public ArtistModel findOne(int id) {
        return artistDao.findOne(id);
    }

    @Override
    public List<ArtistModel> findAll() {
        return artistDao.findAll();
    }

    @Override
    public List<ArtistModel> findAll(Pageble pageble) {
        return artistDao.findAll(pageble);
    }

    @Override
    public List<ArtistModel> findAllByTotallisten() {
        return artistDao.findAllByTotallisten();
    }

    @Override
    public List<ArtistModel> findAllBySearch(Pageble pageble) {
        pageble.setSearchKeyWord("%" +pageble.getSearchKeyWord() + "%");
        return artistDao.findAllBySearch(pageble);
    }

    @Override
    public List<ArtistModel> findAllBySearchStart(Pageble pageble) {
        pageble.setSearchKeyWord(pageble.getSearchKeyWord() + "%");
        return artistDao.findAllBySearch(pageble);
    }

    @Override
    public int getTotalSong(int id) {
        return artistDao.getTotalSong(id);
    }

    @Override
    public int save(ArtistModel artist) {
        return artistDao.save(artist);
    }

    @Override
    public void update(ArtistModel newArtist) {
        artistDao.update(newArtist);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            artistDao.delete(id);
        }
    }

    @Override
    public int getTotalItem() {
        return artistDao.getTotalItem();
    }
}
