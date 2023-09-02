package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/error/*")
public class ErrorController {
    // http://localhost:10000/error/error_page
    @GetMapping("error_page")
    public void errorPage() {;}

    // http://localhost:10000/error/error_page500
    @GetMapping("error_page500")
    public void errorPage500() {;}

    @GetMapping("403")
    public void error403(){;}

    @GetMapping("401")
    public void error401(){;}
}
