package com.walletmusic.dao;

import com.walletmusic.model.SongModel;
import com.walletmusic.paging.Pageble;

import java.util.List;

public interface ISongDAO extends GenericDAO<SongModel>{
    SongModel findOne(int id);
    SongModel findOneWithGenresId(int id);
    SongModel findOneWithArtistAndAlbum(int id);
    SongModel findOneWithArtistAndAlbumAndGenres(int id);

    List<SongModel> findAll(Pageble pageble);
    List<SongModel> findAllWithArtistAndAlbum(Pageble pageble);
    List<SongModel> findAllWithArtistAndAlbumAndGenres(Pageble pageble);
    List<SongModel> findAllByTitle(Pageble pageble);

    List<SongModel> findAllByAlbumName(Pageble pageble);

    List<SongModel> findAllByArtistName(Pageble pageble);
    List<SongModel> findAllInPlaylist(int playlistId);
    List<SongModel> findAllInAlbum(int albumId);

    SongModel findArtistListById( int id);
    int save(SongModel songModel);
    void update(SongModel updateSong);
    void updateCountlisten(int id);
    void delete(int id);
    void deleteGenreOf(int id);
    void deleteSongBy(int id);

    List<Integer> findAllAlbumId();
    int getTotalItem();
    int getTotalItemSearchArtist(String searchKeyWord);
    int getTotalItemSearchAlbum(String searchKeyWord);
    int getTotalItemSearchTitle(String searchKeyWord);

}
