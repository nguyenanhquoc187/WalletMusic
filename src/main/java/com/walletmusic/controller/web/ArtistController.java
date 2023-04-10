package com.walletmusic.controller.web;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.ISongService;
import com.walletmusic.sort.Sorter;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "nghe-si", value = "/nghe-si")
public class ArtistController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Inject
    private IArtistService artistService;
    @Inject
    private IAlbumService albumService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        SongModel songModel = new SongModel();
        int artistId = Integer.parseInt(request.getParameter("id"));
        ArtistModel artist = artistService.findOne(artistId);
        Pageble pageble = new PageRequest(1,100,
                new Sorter("count_listen","desc"),artist.getName(), "artist");
        songModel.setListResult(songService.findAllByArtistName(pageble));
        AlbumModel albumModel = new AlbumModel();
        albumModel.setListResult(albumService.findAllByArtist(artistId));
        request.setAttribute("albumModel",albumModel);
        request.setAttribute("songModel",songModel);
        request.setAttribute("artistModel",artist);
        request.getRequestDispatcher("/views/web/artist.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
