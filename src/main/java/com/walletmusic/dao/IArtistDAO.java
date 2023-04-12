package com.walletmusic.dao;

import com.walletmusic.model.ArtistModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface IArtistDAO extends GenericDAO<ArtistModel> {
    ArtistModel findOne(int id);
    List<ArtistModel> findAll();
    List<ArtistModel> findAll(Pageble pageble);
    List<ArtistModel> findAllByTotallisten();
    List<ArtistModel> findAllBySearch(Pageble pageble);
    int getTotalSong(int id);
    int getTotalAlbum(int id);
    int save(ArtistModel artist);
    void update(ArtistModel newArtist);
    void delete(int id);
    int getTotalItem();
    int getTotalBySearch(String keyword);
}
