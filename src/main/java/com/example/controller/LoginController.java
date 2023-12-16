package com.example.controller;

import com.example.service.UserServiceImpl;
import com.example.vo.UserVo;
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

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login/login";
    }

    @RequestMapping(value="/loginOk", method=RequestMethod.POST)
    public String loginCheck(HttpSession session, UserVo vo) {
        String returnURL = "";
        if ( session.getAttribute("login") != null ){
            session.removeAttribute("login");
        }

        UserVo loginvo = service.getUser(vo);
        if ( loginvo != null ){ // 로그인성공
            System.out.println("로그인성공!");
            session.setAttribute("login", loginvo);
            returnURL = "redirect:/menu/list";
        }
        else { // 로그인실패
            System.out.println("로그인실패!");
            returnURL = "redirect:/login/login";
        }

        return returnURL;
    }

    // 로그아웃하는부분
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login";
    }
}
