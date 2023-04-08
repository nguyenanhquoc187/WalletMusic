package com.walletmusic.controller.web;

import com.walletmusic.model.SongModel;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "nghe-si", value = "/nghe-si")
public class ArtistController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SongModel song = songService.findOneWithArtistAndAlbum(1);
        System.out.println(song.getTitle());
        request.setAttribute("songModel", song);
        request.getRequestDispatcher("/views/web/artist.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
