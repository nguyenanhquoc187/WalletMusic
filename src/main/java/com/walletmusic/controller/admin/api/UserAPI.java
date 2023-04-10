package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.HistoryModel;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IHistoryService;
import com.walletmusic.service.IUserService;
import com.walletmusic.utils.HttpUtil;
import com.walletmusic.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-user"})
public class UserAPI extends HttpServlet {
    @Inject
    private IUserService userService;
    @Inject
    private IHistoryService historyService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        UserModel model = HttpUtil.of(request.getReader()).toModel(UserModel.class);
        model.setRoleId(2);
        model.setAvatar(SystemConstant.DEFAULT_AVATAR);
        if (userService.findByUsername(model.getUsername()) == null) {
            int id = userService.save(model);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(),model);
        } else {
            response.sendRedirect(request.getContextPath()+"/dang-ky?action=register&message=username_exist&alert=error");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        UserModel userModel ;
        userModel = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (userModel != null) {
            int songId = Integer.parseInt(request.getParameter("songId"));
            HistoryModel historyModel = new HistoryModel();
            historyModel.setUserId(userModel.getId());
            historyModel.setSongId(songId);
            historyService.save(historyModel);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(),"{}");
        }


    }
}
