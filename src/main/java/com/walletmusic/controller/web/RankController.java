package com.walletmusic.controller.web;

import com.walletmusic.model.SongModel;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "xep-hang", value = "/xep-hang")
public class RankController extends HttpServlet {

    @Inject
    private ISongService songService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongModel songModel = new SongModel();
        songModel.setListResult(songService.findAllByCountListen());
        request.setAttribute("songModel",songModel);
        request.getRequestDispatcher("/views/web/songrank.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
