package com.walletmusic.mapper;

import com.walletmusic.model.RoleModel;
import com.walletmusic.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        UserModel user = new UserModel();
        try {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("full_name"));
            user.setUsername(resultSet.getString("username"));
            user.setAvatar(resultSet.getString("avatar"));
            user.setBirthDay(resultSet.getDate("birth_day"));
            user.setGender(resultSet.getString("gender"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setPassword(resultSet.getString("password"));
            user.setRoleId(resultSet.getInt("role_id"));
            // try catch vì có thể không inner join với bảng role
            try {
                RoleModel role = new RoleModel();
                role.setName(resultSet.getString("name"));
                user.setRole(role);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }
}
