package com.walletmusic.filter;

import com.walletmusic.model.PlaylistModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IPlaylistService;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlaylistFilter implements Filter {
    @Inject
    private IPlaylistService playlistService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user!= null) {
            PlaylistModel playlistModel = new PlaylistModel();
            playlistModel.setListResult(playlistService.findAllPlaylistByUser(user.getId()));
            request.setAttribute("playlistModel",playlistModel);
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
