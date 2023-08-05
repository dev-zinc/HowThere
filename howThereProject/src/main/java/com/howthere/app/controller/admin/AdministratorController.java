package com.howthere.app.controller.admin;

import com.howthere.app.domain.program.ProgramDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/administrator/*")
public class AdministratorController {
    //http://localhost:10000/administrator/program
    @GetMapping("program")
    public List<ProgramDTO> program() {
        return null;
    }

    //http://localhost:10000/administrator/stay
    @GetMapping("stay")
    public void stay() {;}

    //http://localhost:10000/administrator/reservation
    @GetMapping("reservation")
    public void reserve() {;}

    //http://localhost:10000/administrator/member
    @GetMapping("member")
    public void member() {;}

    //http://localhost:10000/administrator/notice
    @GetMapping("notice")
    public void notice() {;}

    //http://localhost:10000/administrator/notice/detail
    @GetMapping("notice/detail")
    public String noticeDetail() {
        return "/administrator/notice-detail";
    }

    //http://localhost:10000/administrator/notice/write
    @GetMapping("notice/write")
    public String noticeWrite() {
        return "/administrator/notice-write";
    }

    //http://localhost:10000/administrator/inquiry
    @GetMapping("inquiry")
    public void inquiry() {;}

    //http://localhost:10000/administrator/inquiry/detail
    @GetMapping("inquiry/detail")
    public String inquiryDetail() {
        return "/administrator/inquiry-detail";
    }

    //http://localhost:10000/administrator/inquiry/write
    @GetMapping("inquiry/write")
    public String inquiryWrite() {
        return "/administrator/inquiry-write";
    }
}
