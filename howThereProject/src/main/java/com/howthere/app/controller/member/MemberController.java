package com.howthere.app.controller.member;

import com.howthere.app.domain.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    private  final HttpSession session;
    //http://localhost:10000/member/login
    @GetMapping("login")
    public String login() {
        return session.getAttribute("member") == null ? "/member/login" : "redirect:/";
    }

    //http://localhost:10000/member/join
    @GetMapping("join")
    public void join() {;}

    //http://localhost:10000/member/connect
    @GetMapping("connect")
    public void connect() {;}

    @GetMapping("info")
    @ResponseBody
    public Object isLogin(){
        return session.getAttribute("member");
    }
}
