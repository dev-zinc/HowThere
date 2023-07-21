package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/diary/*")
public class DiaryController {
    //http://localhost:10000/diary/list
    @GetMapping("list")
    public void list() {;}

    //http://localhost:10000/diary/article
    @GetMapping("article")
    public void article() {;}

    //http://localhost:10000/diary/write
    @GetMapping("write")
    public void goToWriteForm() {;}

    @PostMapping("write")
    public void write() {;}

    //write 페이지에서 modify
//    //http://localhost:10000/diary/modify
//    @GetMapping("modify")
//    public void goToModifyForm() {;}
//
//    @PostMapping("modify")
//    public void modify() {;}
}
