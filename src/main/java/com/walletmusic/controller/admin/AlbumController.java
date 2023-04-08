package com.walletmusic.controller.admin;

import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.AlbumModel;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IAlbumService;
import com.walletmusic.service.IArtistService;
import com.walletmusic.service.ISongService;
import com.walletmusic.sort.Sorter;
import com.walletmusic.utils.FormUtil;
import com.walletmusic.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "admin-album", value = "/admin-album")
public class AlbumController extends HttpServlet {
    @Inject
    private IAlbumService albumService;
    @Inject
    private ISongService songService;
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlbumModel model = FormUtil.toModel(AlbumModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(albumService.findAll(pageble));
            model.setTotalItem(albumService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            request.setAttribute("albumListAll", songService.findAllAlbumId());
            view = "/views/admin/albums/list.jsp";
        }
        else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                AlbumModel albumWithArtistId = albumService.findOneWithArtistId(model.getId());
                model = albumService.findOne( model.getId() );
                model.setArtistIdList(albumWithArtistId.getArtistIdList());
            }
            request.setAttribute("artist",artistService.findAll());
            view ="/views/admin/albums/edit.jsp";
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
