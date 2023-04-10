package com.walletmusic.controller.web;

import com.walletmusic.dao.IGenresDAO;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.GenresModel;
import com.walletmusic.model.HistoryModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.IGenresService;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "the-loai", value = "/the-loai")
public class GenresController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Inject
    private IGenresService genresService;
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongModel songModel = new SongModel();
        ArtistModel artistModel = new ArtistModel();
        GenresModel genresModel ;
        int genreId = Integer.parseInt(request.getParameter("id"));
        genresModel = genresService.findOne(genreId);
        songModel.setListResult(songService.findAllByGenres(genreId) );

        Set<String> uniqueNames = new HashSet<>();
        List<ArtistModel> artistModelList = new ArrayList<>();
        for (SongModel song : songModel.getListResult()) {
            List<ArtistModel> artists = song.getArtistList();
            for (ArtistModel artist : artists) {
                if (uniqueNames.add(artist.getName())) {
                    artistModelList.add(artist);
                }
            }
        }
        artistModel.setListResult(artistModelList);
        request.setAttribute("genresModel",genresModel);
        request.setAttribute("songModel",songModel);
        request.setAttribute("artistModel",artistModel);
        request.getRequestDispatcher("/views/web/genres.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
