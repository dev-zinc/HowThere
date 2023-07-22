package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    //http://localhost:10000/member/login
    @GetMapping("login")
    public void login() {;}

    //http://localhost:10000/member/join
    @GetMapping("join")
    public void join() {;}

    //http://localhost:10000/member/connect
    @GetMapping("connect")
    public void connect() {;}
}
