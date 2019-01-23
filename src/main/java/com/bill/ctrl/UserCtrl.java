package com.bill.ctrl;

import com.bill.model.User;
import com.bill.model.response.ResponseModel;
import com.bill.model.response.ResponseOneModel;
import com.bill.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserCtrl {

    @Resource
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel add(User u){
        u.init();
        return service.add(u);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseOneModel<User> find(User u){
        return service.find(u);
    }



}
