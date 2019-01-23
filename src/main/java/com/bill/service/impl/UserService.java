package com.bill.service.impl;

import com.bill.dao.impl.UserDao;
import com.bill.model.User;
import com.bill.model.response.ResponseListModel;
import com.bill.model.response.ResponseModel;
import com.bill.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImpl<User> {
    @Autowired
    public UserService(UserDao dao) {
        super.dao = dao;
    }

    @Override
    public ResponseModel add(User user) {
        if (user.getPwd() != null && !user.getPwd().isEmpty()){
            user.setPwd(MD5Util.md5(user.getPwd() + user.getUsername()));
        }
        return super.add(user);
    }

    @Override
    public ResponseListModel<User> list(User user) {
        if (user.getPwd() != null && !user.getPwd().isEmpty()){
            user.setPwd(MD5Util.md5(user.getPwd() + user.getUsername()));
        }
        return super.list(user);
    }
}
