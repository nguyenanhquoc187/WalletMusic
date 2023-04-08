package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.PlaylistModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IPlaylistService;
import com.walletmusic.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlaylistAPI", value = "/api-playlist")
public class PlaylistAPI extends HttpServlet {
    @Inject
    private IPlaylistService playlistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PlaylistModel model = HttpUtil.of(request.getReader()).toModel(PlaylistModel.class);
        model.setImage(SystemConstant.DEFAULT_PLAYLIST);
        int id = playlistService.save(model);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),model);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PlaylistModel model = HttpUtil.of(request.getReader()).toModel(PlaylistModel.class);
        playlistService.delete(model.getId());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),"{}");
    }
}
