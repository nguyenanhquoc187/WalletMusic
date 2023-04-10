package com.walletmusic.filter;

import com.walletmusic.model.SongModel;
import com.walletmusic.service.ISongService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuggestFilter implements Filter {
    @Inject
    private ISongService songService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SongModel songSuggest = new SongModel();
        songSuggest.setListResult(songService.findSuggest());
        request.setAttribute("songSuggest",songSuggest);
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
