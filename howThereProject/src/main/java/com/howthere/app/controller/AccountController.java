package com.howthere.app.controller;

import com.howthere.app.util.OutSeoulUrlConst;
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
    @GetMapping(OutSeoulUrlConst.ACCOUNT_MENU)
    public void menu() {
        ;
    }

    //http://localhost:10000/account/setting
    @GetMapping(OutSeoulUrlConst.ACCOUNT_SETTING)
    public void setting() {
        ;
    }

    //http://localhost:10000/account/payment
    @GetMapping(OutSeoulUrlConst.ACCOUNT_PAYMENT)
    public void payment() {
        ;
    }

    //http://localhost:10000/account/diary
    @GetMapping(OutSeoulUrlConst.ACCOUNT_DIARY)
    public void diary() {
        ;
    }

    //http://localhost:10000/account/program
    @GetMapping(OutSeoulUrlConst.ACCOUNT_PROGRAM)
    public void program() {
        ;
    }
}
