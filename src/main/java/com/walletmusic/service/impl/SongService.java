package com.walletmusic.service.impl;

import com.walletmusic.dao.ISongDAO;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import java.util.List;

public class SongService implements ISongService {
    @Inject
    private ISongDAO songDao ;
//    public SongService() {
//        songDao = new SongDAO();
//    }
    @Override
    public SongModel findOne(int id) {
        return songDao.findOne(id);
    }

    @Override
    public SongModel findOneWithArtistAndAlbum(int id) {
        return songDao.findOneWithArtistAndAlbum(id);
    }

    @Override
    public SongModel findOneWithArtistAndAlbumAndGenres(int id) {
        return songDao.findOneWithArtistAndAlbumAndGenres(id);
    }

    @Override
    public List<SongModel> findAll(Pageble pageble) {
        return songDao.findAll(pageble);
    }

    @Override
    public List<SongModel> findAllWithArtistAndAlbumAndGenres(Pageble pageble) {
        return songDao.findAllWithArtistAndAlbumAndGenres(pageble);
    }

    @Override
    public List<SongModel> findAllByTitle(Pageble pageble) {
        return songDao.findAllByTitle(pageble);
    }

    @Override
    public List<SongModel> findAllByAlbumName(Pageble pageble) {
        return songDao.findAllByAlbumName(pageble);
    }

    @Override
    public List<SongModel> findAllWithArtistAndAlbum(Pageble pageble) {
        return songDao.findAllWithArtistAndAlbum(pageble);
    }

    @Override
    public int save(SongModel song) {
        return songDao.save(song);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id: ids) {
            songDao.delete(id);
        }
    }

    @Override
    public void update(SongModel updateSong) {
        songDao.update(updateSong);
    }

    @Override
    public List<Integer> findAllAlbumId() {
        return songDao.findAllAlbumId();
    }

    @Override
    public List<SongModel> findAllByArtistName(Pageble pageble) {

        return songDao.findAllByArtistName(pageble);
    }

    @Override
    public int getTotalItem() {
        return songDao.getTotalItem();
    }

    @Override
    public int getTotalItemSearchArtist(String keyword) {
        return songDao.getTotalItemSearchArtist(keyword);
    }

    @Override
    public int getTotalItemSearchAlbum(String keyword) {
        return songDao.getTotalItemSearchAlbum(keyword);
    }

    @Override
    public int getTotalItemSearchTitle(String keyword) {
        return songDao.getTotalItemSearchTitle(keyword);
    }
}
