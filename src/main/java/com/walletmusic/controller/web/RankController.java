package com.walletmusic.controller.web;

import com.walletmusic.model.SongModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.ISongService;
import com.walletmusic.utils.SessionUtil;

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
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        SongModel songSuggest = new SongModel();
        if (user != null) {;
            songSuggest.setListResult(songService.findSongSuggest(user.getId()));
        } else {
            songSuggest.setListResult(songService.findSuggest());
        }
        SessionUtil.getInstance().putValue(request, "songSuggest", songSuggest);
        SongModel songModel = new SongModel();
        songModel.setListResult(songService.findAllByCountListen());
        request.setAttribute("songModel",songModel);
        request.getRequestDispatcher("/views/web/songrank.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
