package com.walletmusic.dao.impl;

import com.walletmusic.dao.IPlaylistDAO;
import com.walletmusic.mapper.PlaylistMapper;
import com.walletmusic.model.PlaylistModel;

import java.util.List;

public class PlaylistDAO extends AbstractDAO<PlaylistModel> implements IPlaylistDAO {
    @Override
    public int save(PlaylistModel playlist) {
        String sql = "INSERT INTO playlists(name,image,user_id) VALUES(?,?, ?)";
        return insert(sql,playlist.getName(),playlist.getImage(),playlist.getUserId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        deletePlaylistIncludes(id);
        update(sql,id);
    }

    @Override
    public List<PlaylistModel> findAllPlaylistByUser(int userId) {
        String sql = "SELECT * FROM playlists WHERE user_id = ?";
        return query(sql,new PlaylistMapper(),userId);
    }

    @Override
    public void deletePlaylistIncludes(int playlistid) {
        String sql = "DELETE FROM playlist_includes WHERE playlist_id = ?";
        update(sql,playlistid);
    }
}
