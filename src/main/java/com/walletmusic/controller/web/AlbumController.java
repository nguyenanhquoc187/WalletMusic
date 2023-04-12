package com.walletmusic.controller.web;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.ISongService;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "album", value = "/album")
public class AlbumController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Inject
    private IAlbumService albumService;
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
        int albumId = Integer.parseInt(request.getParameter("id"));
        AlbumModel albumModel = albumService.findOne(albumId);
        SongModel songModel = new SongModel();
        songModel.setListResult(songService.findAllInAlbum(albumId));
        request.setAttribute("songModel",songModel);
        request.setAttribute("albumModel",albumModel);
        request.getRequestDispatcher("/views/web/albumdetail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
