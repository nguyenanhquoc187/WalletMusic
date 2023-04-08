package com.walletmusic.dao.impl;

import com.walletmusic.dao.IUserDAO;
import com.walletmusic.mapper.SongMapper;
import com.walletmusic.mapper.UserMapper;
import com.walletmusic.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT * FROM users AS u INNER JOIN roles AS r ON r.id = u.role_id " +
                "WHERE username = ? AND password = ?";
        List<UserModel> users = query(sql, new UserMapper(), userName, password);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public int save(UserModel user) {
        String sql = "INSERT INTO users(full_name,username,avatar,birth_day,gender,email,phone_number,password,role_id) " +
                " VALUES(?,?,?,?,?,?,?,?,?) ";
        int id = insert(sql,user.getName(), user.getUsername(),user.getAvatar(),user.getBirthDay(),
                user.getGender(),user.getEmail(),user.getPhoneNumber(),user.getPassword(),user.getRoleId());
        return id;
    }

    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<UserModel> users = query(sql,new UserMapper(),username);
        return users.isEmpty() ? null : users.get(0);
    }
}
