package com.walletmusic.service;

import com.walletmusic.model.PlaylistModel;

import java.util.List;

public interface IPlaylistService {
    PlaylistModel findOne(int playlistId);
    int save(PlaylistModel playlist);
    boolean saveSong(int playlistId, int songId );
    void delete(int id);
    void deleteOneSong(int playlistId, int songId);

    List<PlaylistModel> findAllPlaylistByUser(int userId);
}
