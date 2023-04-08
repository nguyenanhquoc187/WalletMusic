package com.walletmusic.service.impl;

import com.walletmusic.dao.IUserDAO;
import com.walletmusic.model.UserModel;
import com.walletmusic.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDao;
    @Override
    public UserModel findByUserNameAndPassword(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName,password);
    }

    @Override
    public int save(UserModel userModel) {
        return userDao.save(userModel);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
