package com.walletmusic.controller.web;

import com.walletmusic.model.*;
import com.walletmusic.service.*;
import com.walletmusic.service.impl.SongService;
import com.walletmusic.utils.FormUtil;
import com.walletmusic.utils.MessageUtil;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/dang-ky", "/thoat"})
public class HomeController extends HttpServlet {
    @Inject
    private ISongService songService;
    @Inject
    private IArtistService artistService;
    @Inject
    private IUserService userService;
    @Inject
    private IPlaylistService playlistService;
    @Inject
    private IAlbumService albumService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user != null) {
            PlaylistModel playlistModel = new PlaylistModel();
            playlistModel.setListResult(playlistService.findAllPlaylistByUser(
                    ((UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL")).getId() ));
            request.setAttribute("playlistModel",playlistModel);
        }
        AlbumModel albumModel = new AlbumModel();
        albumModel.setListResult(albumService.findAllByCountListen());
        ArtistModel artistModel = new ArtistModel();
        artistModel.setListResult(artistService.findAllByTotallisten() );
        request.setAttribute("albumModel",albumModel);
        request.setAttribute("artistModel",artistModel);
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            String alert = request.getParameter("alert");
            String message = request.getParameter("message");
            if (message != null && alert != null) {
                request.setAttribute("message", MessageUtil.getMessageLogin(message));
                request.setAttribute("alert", alert);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath()+"/trang-chu");
        } else if (action != null && action.equals("register")) {
            String alert = request.getParameter("alert");
            String message = request.getParameter("message");
            if (message != null && alert != null) {
                request.setAttribute("message", MessageUtil.getMessageLogin(message));
                request.setAttribute("alert", alert);
            }
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel model = FormUtil.toModel(UserModel.class, request);
            model = userService.findByUserNameAndPassword(model.getUsername(), model.getPassword());
            if (model != null) {
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
                if (model.getRole().getName().equals("USER")) {
                    response.sendRedirect(request.getContextPath()+"/trang-chu?action=login&message=login_success&alert=success");
                } else if (model.getRole().getName().equals("ADMIN")) {
                    response.sendRedirect(request.getContextPath()+"/admin-home");
                }


            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=error");
            }
        }
    }
}
