package com.howthere.app.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help/*")
@Slf4j
@RequiredArgsConstructor
public class HelpController {

    // http://localhost:10000/help/help
    @GetMapping("help")
    public void program() {;}
}
