package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.model.PlaylistModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IPlaylistService;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "api-playlistInc", value = "/api-playlistInc")
public class PlaylistIncAPI extends HttpServlet {
    @Inject
    private IPlaylistService playlistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user != null) {
            int playlistId = Integer.parseInt(request.getParameter("playlistId"));
            int songId = Integer.parseInt(request.getParameter("songId"));
            boolean success = playlistService.saveSong(playlistId,songId);
            if (success) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(response.getOutputStream(),"{}");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user != null) {
            int playlistId = Integer.parseInt(request.getParameter("playlistId"));
            int songId = Integer.parseInt(request.getParameter("songId"));
            playlistService.deleteOneSong(playlistId,songId);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(),"{}");

        }
    }
}
