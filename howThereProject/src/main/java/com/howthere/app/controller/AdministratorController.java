package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/administrator/*")
public class AdministratorController {
    @GetMapping("program")
    public void program() {;}

    @GetMapping("stay")
    public void stay() {;}

    @GetMapping("reservation")
    public void reserve() {;}

    @GetMapping("member")
    public void member() {;}

    @GetMapping("notice")
    public void notice() {;}

    @GetMapping("inquiry")
    public void inquiry() {;}

    @GetMapping("inquiry-detail")
    public void inquiryDetail() {;}
}
