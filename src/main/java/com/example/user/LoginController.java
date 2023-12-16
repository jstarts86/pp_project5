package com.example.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/login")
public class LoginController {
    @Autowired
    UserServiceImpl service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/loginOk", method = RequestMethod.POST)
    public String loginCheck(HttpSession session, UserVo vo){
        System.out.println("Please try to login");
        String returnURL = "";
        if (session.getAttribute("login") != null) {
            session.removeAttribute("login");
            System.out.println("Login start");
        }

        UserVo loginvo = service.getUser(vo);
        if(loginvo != null){
            System.out.println("로그인 성공 login success");
            session.setAttribute("login", loginvo);
            returnURL = "redirect:/menu/list";
        } else {
            System.out.println("로그인 실패! login failed");
            returnURL = "redirect:/login/login";
        }
        return returnURL;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login/login";
    }
}