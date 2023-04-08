package com.walletmusic.service.impl;

import com.walletmusic.dao.IPlaylistDAO;
import com.walletmusic.model.PlaylistModel;
import com.walletmusic.service.IPlaylistService;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {
    @Inject
    private IPlaylistDAO playlistDao;
    @Override
    public int save(PlaylistModel playlist) {
        return playlistDao.save(playlist);
    }

    @Override
    public void delete(int id) {
        playlistDao.delete(id);
    }

    @Override
    public List<PlaylistModel> findAllPlaylistByUser(int userId) {
        return playlistDao.findAllPlaylistByUser(userId);
    }
}
