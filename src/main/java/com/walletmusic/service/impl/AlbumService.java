package com.walletmusic.service.impl;

import com.walletmusic.dao.IAlbumDAO;
import com.walletmusic.model.AlbumModel;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IAlbumService;

import javax.inject.Inject;
import java.util.List;

public class AlbumService implements IAlbumService {

    @Inject
    private IAlbumDAO albumDao;
    @Override
    public AlbumModel findOne(int id) {
        return albumDao.findOne(id);
    }

    @Override
    public AlbumModel findOneWithArtistId(int id) {
        return albumDao.findOneWithArtistId(id);
    }

    @Override
    public List<AlbumModel> findAll() {
        return albumDao.findAll();
    }

    @Override
    public List<AlbumModel> findAll(Pageble pageble) {
        return albumDao.findAll(pageble);
    }

    @Override
    public List<AlbumModel> findAllByCountListen() {
        return albumDao.findAllByCountListen("");
    }

    @Override
    public List<AlbumModel> findAllByArtist(int artistId) {
        return albumDao.findAllByArtist(artistId);
    }

    @Override
    public List<AlbumModel> findAllBySearch(Pageble pageble) {
        pageble.setSearchKeyWord("%"+pageble.getSearchKeyWord() + "%");
        return albumDao.findAllBySearch(pageble);
    }

    @Override
    public List<AlbumModel> findAllBySearchStart(Pageble pageble) {
        pageble.setSearchKeyWord(pageble.getSearchKeyWord() + "%");
        return albumDao.findAllBySearch(pageble);
    }

    @Override
    public int getTotalSong(int id) {
        return albumDao.getTotalSong(id);
    }

    @Override
    public int save(AlbumModel album) {
        return albumDao.save(album);
    }

    @Override
    public void update(AlbumModel newAlbum) {
        albumDao.update(newAlbum);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            albumDao.delete(id);
        }
    }

    @Override
    public int getTotalItem() {
        return albumDao.getTotalItem();
    }
}
