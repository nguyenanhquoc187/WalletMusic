package com.walletmusic.controller.web;

import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.ISongService;
import com.walletmusic.sort.Sorter;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "tim-kiem", value = "/tim-kiem")
public class SearchController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Inject
    private IAlbumService albumService;
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        SongModel songSuggest = new SongModel();
        if (user != null) {;
            songSuggest.setListResult(songService.findSongSuggest(user.getId()));
        } else {
            songSuggest.setListResult(songService.findSongRankSuggest());
        }
        SessionUtil.getInstance().putValue(request, "songSuggest", songSuggest);
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        SongModel songByTitle = new SongModel();
        Pageble pagebleTitle = new PageRequest(1,100,
                new Sorter("count_listen","desc"),keyword,"title" );
        songByTitle.setListResult(songService.findAllByTitle(pagebleTitle));

        AlbumModel albumModel = new AlbumModel();
        Pageble pagebleAlbum = new PageRequest(1,5,
                new Sorter("name","desc"), keyword,"album" );
        albumModel.setListResult(albumService.findAllBySearch(pagebleAlbum));

        ArtistModel artistModel = new ArtistModel();
        Pageble pagebleArtist= new PageRequest(1,5,
                new Sorter("name","desc"), keyword,"artist" );
        artistModel.setListResult(artistService.findAllBySearch(pagebleArtist));

        request.setAttribute("songModel",songByTitle);
        request.setAttribute("albumModel",albumModel);
        request.setAttribute("artistModel",artistModel);
        request.getRequestDispatcher("/views/web/searchResults.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
