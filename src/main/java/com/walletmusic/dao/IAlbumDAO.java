package com.walletmusic.dao;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface IAlbumDAO extends GenericDAO<AlbumModel> {
    AlbumModel findOne(int id);
    AlbumModel findOneWithArtistId(int id);
    List<AlbumModel> findAll();

    List<AlbumModel> findAll(Pageble pageble);
    List<AlbumModel> findAllByCountListen(String search);
    List<AlbumModel> findAllByArtist(int artistId);
    List<AlbumModel> findAllBySearch(Pageble pageble);
    int getTotalSong(int id);
    int save(AlbumModel album);
    void update(AlbumModel newAlbum);
    void delete(int id);
    void deleteAlbumBy(int id);
    int getTotalItem();
}
