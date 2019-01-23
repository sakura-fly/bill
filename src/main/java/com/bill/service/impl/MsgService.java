package com.bill.service.impl;

import com.bill.dao.impl.MsgDao;
import com.bill.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService extends BaseServiceImpl<Msg> {

    @Autowired
    public MsgService(MsgDao dao) {
        super.dao = dao;
    }
}
