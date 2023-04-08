package com.walletmusic.service;

import com.walletmusic.model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPassword(String userName, String password);
    int save(UserModel userModel);
    UserModel findByUsername(String username);
}
