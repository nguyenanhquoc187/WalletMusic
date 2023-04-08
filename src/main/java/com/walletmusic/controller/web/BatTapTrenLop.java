package com.walletmusic.controller.web;

import com.walletmusic.dao.impl.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/bai-tap"})
public class BatTapTrenLop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        String sql = "SELECT name FROM albums WHERE id = 2";
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            String result = null;
            result = resultSet.getString("name");
            System.out.println(result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
