package com.howthere.app.controller.admin;

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
    // http://localhost:10000/announcement/announcement_list
    @GetMapping("announcement_list")
    public void announcementList() {;}

    // http://localhost:10000/announcement/announcement
    @GetMapping("announcement")
    public void announcement() {;}
}
