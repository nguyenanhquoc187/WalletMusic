package com.walletmusic.service;

import com.walletmusic.model.ArtistModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface IArtistService {
    ArtistModel findOne(int id);
    List<ArtistModel> findAll();
    List<ArtistModel> findAll(Pageble pageble);
    List<ArtistModel> findAllByTotallisten();

    public List<ArtistModel> findAllBySearch(Pageble pageble);
    public List<ArtistModel> findAllBySearchStart(Pageble pageble);
    int getTotalSong(int id);
    int save(ArtistModel artist);
    void update(ArtistModel newArtist);
    void delete(Integer[] ids);
    int getTotalItem();
}
