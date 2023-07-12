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
    @GetMapping("menu")
    public void menu() {;}

    @GetMapping("setting")
    public void setting() {;}

    @GetMapping("payment")
    public void payment() {;}

    @GetMapping("diary")
    public void diary() {;}

    @GetMapping("program")
    public void program() {;}
}
