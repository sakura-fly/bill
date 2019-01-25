package com.bill.service.impl;

import com.bill.dao.impl.MsgDao;
import com.bill.model.Msg;
import com.bill.model.response.ResponseListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService extends BaseServiceImpl<Msg> {

    private MsgDao msgDao;

    @Autowired
    public MsgService(MsgDao dao) {
        super.dao = dao;
        this.msgDao = dao;
    }


    public ResponseListModel<Msg> list(Msg msg, String starttime, String endtime) {
        ResponseListModel<Msg> r = new ResponseListModel<>();
        try {
            r.setData(msgDao.list(msg,starttime,endtime));
            r.setCount(dao.count(msg));
            resSuccess(r);
        } catch (Exception e) {
            e.printStackTrace();
            reserr(r);
        }
        return r;
    }

    public double allprice(Msg msg, String starttime, String endtime){
        try {
            return msgDao.allPrice(msg,starttime,endtime);
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
