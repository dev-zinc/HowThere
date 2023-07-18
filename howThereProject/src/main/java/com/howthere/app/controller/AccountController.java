package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/account/*")
public class AccountController {
    //http://localhost:10000/account/menu
    @GetMapping("menu")
    public void menu() {;}

    //http://localhost:10000/account/setting
    @GetMapping("setting")
    public void setting() {;}

    //http://localhost:10000/account/payment
    @GetMapping("payment")
    public void payment() {;}

    //http://localhost:10000/account/diary
    @GetMapping("diary")
    public void diary() {;}

    //http://localhost:10000/account/program
    @GetMapping("program")
    public void program() {;}
}
