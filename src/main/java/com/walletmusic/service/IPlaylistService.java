package com.walletmusic.service;

import com.walletmusic.model.PlaylistModel;

import java.util.List;

public interface IPlaylistService {
    int save(PlaylistModel playlist);
    void delete(int id);

    List<PlaylistModel> findAllPlaylistByUser(int userId);
}
