package com.walletmusic.service;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface IAlbumService {
    AlbumModel findOne(int id);
    AlbumModel findOneWithArtistId(int id);
    List<AlbumModel> findAll();
    List<AlbumModel> findAll(Pageble pageble);
    List<AlbumModel> findAllByCountListen();
    List<AlbumModel> findAllByArtist(int artistId);
    List<AlbumModel> findAllBySearch(Pageble pageble);
    List<AlbumModel> findAllBySearchStart(Pageble pageble);
    int getTotalSong(int id);
    int save(AlbumModel album);
    void update(AlbumModel newAlbum);
    void delete(Integer[] ids);
    int getTotalItem();
    public int getTotalItemBySearch(String keyword);
}
