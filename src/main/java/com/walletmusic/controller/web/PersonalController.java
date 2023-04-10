package com.walletmusic.controller.web;

import com.walletmusic.model.ArtistModel;
import com.walletmusic.model.HistoryModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IHistoryService;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ca-nhan", value = "/ca-nhan")
public class PersonalController extends HttpServlet {
    @Inject
    private IHistoryService historyService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user == null) response.sendRedirect( request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=error");
        else {
            List<HistoryModel> historyModelList ;
            historyModelList = historyService.findAllHistoryByUser(user.getId());
            Set<String> uniqueNames = new HashSet<>();
            List<ArtistModel> artistModelList = new ArrayList<>();
            for (HistoryModel historyModel : historyModelList) {
                List<ArtistModel> artists = historyModel.getSong().getArtistList();
                for (ArtistModel artist : artists) {
                    if (uniqueNames.add(artist.getName())) {
                        artistModelList.add(artist);
                    }
                }
            }

            ArtistModel artistModel = new ArtistModel();
            artistModel.setListResult(artistModelList);
            request.setAttribute("artistModel",artistModel);
            request.setAttribute("historyModel",historyModelList);
            request.getRequestDispatcher("/views/web/personal.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
