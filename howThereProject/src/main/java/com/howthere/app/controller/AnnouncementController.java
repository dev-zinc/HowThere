package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/announcement/*")
public class AnnouncementController {
    @GetMapping("announcement_list")
    public void announcementList() {;}

    @GetMapping("announcement")
    public void announcement() {;}
}
