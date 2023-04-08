package com.walletmusic.dao;

import com.walletmusic.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
    UserModel findByUserNameAndPassword(String userName, String password);
    int save(UserModel user);
    UserModel findByUsername(String username);
}
