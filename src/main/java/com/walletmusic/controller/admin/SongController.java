package com.walletmusic.controller.admin;

import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.IGenresService;
import com.walletmusic.service.ISongService;
import com.walletmusic.sort.Sorter;
import com.walletmusic.utils.FormUtil;
import com.walletmusic.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-song"})
public class SongController extends HttpServlet {

    @Inject
    private ISongService songService;
    @Inject
    private IGenresService genresService;
    @Inject
    private IAlbumService albumService;
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SongModel model = FormUtil.toModel(SongModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            if ( (model.getSearch() == null || model.getSearch().equals("")) && model.getSearchField() == null) {
                Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                        new Sorter(model.getSortName(), model.getSortBy()));
                model.setListResult(songService.findAllWithArtistAndAlbum(pageble));
                model.setTotalItem(songService.getTotalItem());
                model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            } else {
                if (model.getSearchField() == null) model.setSearchField("title");
                Pageble pageble = new PageRequest(model.getPage(),model.getMaxPageItem(),
                        new Sorter(model.getSortName(), model.getSortBy()), model.getSearch() , model.getSearchField() );
                if (pageble.getSearchField().equals("artist") ) {
                    model.setListResult(songService.findAllByArtistName(pageble));
                    model.setTotalItem(songService.getTotalItemSearchArtist(model.getSearch()));
                }
                else if (pageble.getSearchField().equals("album") ) {
                    model.setListResult(songService.findAllByAlbumName(pageble));
                    model.setTotalItem(songService.getTotalItemSearchAlbum(model.getSearch()));
                } else {
                    model.setListResult(songService.findAllByTitle(pageble));
                    model.setTotalItem(songService.getTotalItemSearchTitle(model.getSearch()));
                }
                model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            }
            view = "/views/admin/songs/list.jsp";
        }
        else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = songService.findOneWithArtistAndAlbumAndGenres( model.getId());
//                model.setLyrics(ConvertLyrics.convertOut(model.getLyrics()));
            }
            request.setAttribute("genres", genresService.findAll());
            request.setAttribute("artist",artistService.findAll());
            request.setAttribute("album",albumService.findAll());
            view ="/views/admin/songs/edit.jsp";
        }

        MessageUtil.showMessage(request);


        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
