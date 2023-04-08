package com.walletmusic.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletmusic.constant.SystemConstant;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IUserService;
import com.walletmusic.utils.HttpUtil;

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
}
