package com.walletmusic.controller.admin;

import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.paging.PageRequest;
import com.walletmusic.paging.Pageble;
import com.walletmusic.service.IArtistService;
import com.walletmusic.sort.Sorter;
import com.walletmusic.utils.FormUtil;
import com.walletmusic.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "admin-artist", value = "/admin-artist")
public class ArtistController extends HttpServlet {
    @Inject
    private IArtistService artistService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArtistModel model = FormUtil.toModel(ArtistModel.class, request);
        String view = "";
        if (model.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
                    new Sorter(model.getSortName(), model.getSortBy()));
            model.setListResult(artistService.findAll(pageble));
            model.setTotalItem(artistService.getTotalItem());
            model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
            view = "/views/admin/artists/list.jsp";
        }
        else if (model.getType().equals(SystemConstant.EDIT)) {
            if (model.getId() != null) {
                model = artistService.findOne( model.getId() );
            }
            view ="/views/admin/artists/edit.jsp";
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
