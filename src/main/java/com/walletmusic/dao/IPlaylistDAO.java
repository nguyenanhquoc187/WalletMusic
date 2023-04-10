package com.walletmusic.dao;

import com.walletmusic.model.PlaylistModel;

import java.util.List;

public interface IPlaylistDAO extends GenericDAO<PlaylistModel> {
    PlaylistModel findOne(int playlistId);
    int save(PlaylistModel playlist);
    void delete(int id);
    void deleteOneSong(int playlistId, int songId);
    List<PlaylistModel> findAllPlaylistByUser(int userId);
    boolean saveSong(int playlistId, int songId);
    void deletePlaylistIncludes(int playlistid);
    int countSongInPlaylist(int playlistId);
}
