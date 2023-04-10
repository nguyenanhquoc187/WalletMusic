package com.walletmusic.controller.web;

import com.walletmusic.model.SongModel;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "bai-hat", value = "/bai-hat")
public class SongController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int songId = Integer.parseInt(request.getParameter("id"));
        SongModel songModel;
        songModel = songService.findOneWithArtistAndAlbum(songId);
        String[] lyrics = songModel.getLyrics().split("\n");
        request.setAttribute("songModel",songModel);
        request.setAttribute("lyrics",lyrics);
        request.getRequestDispatcher("/views/web/songdetail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
