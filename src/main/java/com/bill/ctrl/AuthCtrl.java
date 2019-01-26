package com.bill.ctrl;

import com.bill.model.User;
import com.bill.model.response.ResponseOneModel;
import com.bill.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthCtrl {

    @Resource
    private UserService service;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseOneModel<User> login(User user, HttpSession session){
        ResponseOneModel<User> r = service.find(user);
        if (r.getModel() != null)
            session.setAttribute("user", r.getModel());
        return r;
    }
}
