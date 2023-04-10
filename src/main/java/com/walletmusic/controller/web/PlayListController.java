package com.walletmusic.controller.web;

import com.walletmusic.model.PlaylistModel;
import com.walletmusic.model.SongModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IPlaylistService;
import com.walletmusic.service.ISongService;
import com.walletmusic.utils.MessageUtil;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/playlist"})
public class PlayListController extends HttpServlet {
    @Inject
    private IPlaylistService playlistService;
    @Inject
    private ISongService songService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user == null) response.sendRedirect( request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=error");
        else {

            String action = request.getParameter("action");
            String alert = request.getParameter("alert");
            String message = request.getParameter("message");
            if (action != null && action.equals("create")) {

                if (message != null && alert != null) {
                    request.setAttribute("message", MessageUtil.getMessageLogin(message));
                    request.setAttribute("alert", alert);
                };
            } else if (action != null && action.equals("delete")) {
                if (message != null && alert != null) {
                    request.setAttribute("message", MessageUtil.getMessageLogin(message));
                    request.setAttribute("alert", alert);
                }
            }
            PlaylistModel model = new PlaylistModel();
            model.setListResult(playlistService.findAllPlaylistByUser(
                    ((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getId() ));
            request.setAttribute("playlistModel",model);
            String playlistIdRaw = request.getParameter("id");
            if (playlistIdRaw != null) {
                int playlistId = Integer.parseInt(playlistIdRaw);
                SongModel songModel = new SongModel();
                songModel.setListResult(songService.findAllInPlaylist(playlistId) );
                PlaylistModel playlistModel = playlistService.findOne(playlistId) ;
                request.setAttribute("songModel",songModel);
                request.setAttribute("model",playlistModel);
                request.getRequestDispatcher("/views/web/playlistdetail.jsp").forward(request,response);
            } else {
                request.getRequestDispatcher("/views/web/playlist.jsp").forward(request,response);
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
