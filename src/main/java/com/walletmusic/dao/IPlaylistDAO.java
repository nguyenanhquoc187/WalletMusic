package com.walletmusic.dao;

import com.walletmusic.model.PlaylistModel;

import java.util.List;

public interface IPlaylistDAO extends GenericDAO<PlaylistModel> {
    int save(PlaylistModel playlist);
    void delete(int id);
    List<PlaylistModel> findAllPlaylistByUser(int userId);
    void deletePlaylistIncludes(int playlistid);
}
