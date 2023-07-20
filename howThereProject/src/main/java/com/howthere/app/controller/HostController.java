package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host/*")
@Slf4j
@RequiredArgsConstructor
public class HostController {
//    http://localhost:10000/host/write
    @GetMapping("write")
    public void write(){;}

//    http://localhost:10000/host/inn
    @GetMapping("inn")
    public void inn(){;}
}
