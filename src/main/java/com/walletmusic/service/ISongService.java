package com.walletmusic.service;

import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface ISongService {
    public SongModel findOne(int id);
    SongModel findOneWithArtistAndAlbum(int id);
    SongModel findOneWithArtistAndAlbumAndGenres(int id);
    List<SongModel> findAll(Pageble pageble);
    List<SongModel> findAllWithArtistAndAlbumAndGenres(Pageble pageble);
    List<SongModel> findAllByTitleStart(Pageble pageble);
    List<SongModel> findAllByTitle(Pageble pageble);
    List<SongModel> findAllByAlbumName(Pageble pageble);
    List<SongModel> findAllWithArtistAndAlbum(Pageble pageble);
    public int save(SongModel song);
    public void delete(Integer[] ids);
    public void update(SongModel updateSong);
    List<Integer> findAllAlbumId();
    List<SongModel> findAllByArtistName(Pageble pageble);
    List<SongModel> findAllByCountListen();
    List<SongModel> findSuggest();
    List<SongModel> findAllByGenres(int genreId);
    List<SongModel> findAllInPlaylist(int playlistId);
    List<SongModel> findAllInAlbum(int albumId);
    public int getTotalItem();
    int getTotalItemSearchArtist(String keyword);
    int getTotalItemSearchAlbum(String keyword);
    int getTotalItemSearchTitle(String searchKeyWord);
}
