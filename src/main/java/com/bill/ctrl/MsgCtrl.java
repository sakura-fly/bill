package com.bill.ctrl;

import com.bill.model.Msg;
import com.bill.model.User;
import com.bill.model.response.ResponseListModel;
import com.bill.model.response.ResponseModel;
import com.bill.service.impl.MsgService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/msg")
public class MsgCtrl {

    @Resource
    private MsgService service;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseListModel<Msg> list(Msg msg, String starttime, String endtime) {
        return service.list(msg,starttime,endtime);
    }
    @RequestMapping(value = "/allprice", method = RequestMethod.GET)
    @ResponseBody
    public double allprice(Msg msg, String starttime, String endtime) {
        return service.allprice(msg,starttime,endtime);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel add(Msg msg, HttpSession session) {
        msg.setUserid((((User) session.getAttribute("user")).getId()));
        msg.init();
        return service.add(msg);
    }


}
