package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "api-search", value = "/api-search")
public class SearchAPI extends HttpServlet {

    @Inject
    private ISongService songService;
    @Inject
    private IAlbumService albumService;
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        SongModel songByTitle = new SongModel();
        String keyword = request.getParameter("keyword");
        Pageble pagebleTitle = new PageRequest(1,5,
                new Sorter("count_listen","desc"), keyword+"%","title" );
        songByTitle.setListResult(songService.findAllByTitleStart(pagebleTitle));

        AlbumModel albumModel = new AlbumModel();
        Pageble pagebleAlbum = new PageRequest(1,5,
                new Sorter("name","desc"), keyword+"%","album" );
        albumModel.setListResult(albumService.findAllBySearchStart(pagebleAlbum));

        ArtistModel artistModel = new ArtistModel();
        Pageble pagebleArtist= new PageRequest(1,5,
                new Sorter("name","desc"), keyword+"%","artist" );
        artistModel.setListResult(artistService.findAllBySearchStart(pagebleArtist));
        ArrayList<String> result = new ArrayList<>();
        for (SongModel song : songByTitle.getListResult()) {
            result.add(song.getTitle());
        }
        for (AlbumModel album : albumModel.getListResult()) {
            result.add(album.getName());
        }
        for (ArtistModel artist : artistModel.getListResult()) {
            result.add(artist.getName());
        }
        Map<String, ArrayList<String> > json = new HashMap<>();
        json.put("result",result);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
