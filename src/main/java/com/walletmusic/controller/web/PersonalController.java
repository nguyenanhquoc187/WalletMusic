package com.walletmusic.controller.web;

import com.walletmusic.model.UserModel;
import com.walletmusic.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ca-nhan", value = "/ca-nhan")
public class PersonalController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel user = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
        if (user == null) response.sendRedirect( request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=error");
        else request.getRequestDispatcher("/views/web/personal.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
