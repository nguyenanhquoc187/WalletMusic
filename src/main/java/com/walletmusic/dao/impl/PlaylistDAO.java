package com.walletmusic.dao.impl;

import com.walletmusic.dao.IPlaylistDAO;
import com.walletmusic.mapper.PlaylistMapper;
import com.walletmusic.model.PlaylistModel;
import com.walletmusic.model.SongModel;

import java.util.List;

public class PlaylistDAO extends AbstractDAO<PlaylistModel> implements IPlaylistDAO {
    @Override
    public PlaylistModel findOne(int playlistId) {
        String sql = "SELECT * FROM playlists WHERE id = ?";
        List<PlaylistModel> playlist = query(sql,new PlaylistMapper(),playlistId);
        return playlist.isEmpty() ? null : playlist.get(0);
    }

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
    public void deleteOneSong(int playlistId, int songId) {
        String sql = "DELETE FROM playlist_includes WHERE playlist_id = ? and song_id = ?";
        update(sql,playlistId,songId);
    }

    @Override
    public List<PlaylistModel> findAllPlaylistByUser(int userId) {
        String sql = "SELECT * FROM playlists WHERE user_id = ?";
        return query(sql,new PlaylistMapper(),userId);
    }

    @Override
    public boolean saveSong(int playlistId, int songId) {
        String sql = "INSERT INTO playlist_includes(playlist_id,song_id) VALUES(?, ?)";
        int countOld = countSongInPlaylist(playlistId);
        update(sql,playlistId,songId);
        int countNew = countSongInPlaylist(playlistId);
        return (countNew != countOld);
    }

    @Override
    public void deletePlaylistIncludes(int playlistid) {
        String sql = "DELETE FROM playlist_includes WHERE playlist_id = ?";
        update(sql,playlistid);
    }

    @Override
    public int countSongInPlaylist(int playlistId) {
        String sql = "SELECT count(*) FROM playlist_includes WHERE playlist_id = ?";
        return count(sql,playlistId);
    }
}
