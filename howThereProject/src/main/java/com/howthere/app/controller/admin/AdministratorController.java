package com.howthere.app.controller.admin;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/administrator/*")
public class AdministratorController {
    private final ProgramService programService;

    //http://localhost:10000/administrator/program
    @GetMapping("program")
    public void program() {;}

    @GetMapping("api/program")
    @ResponseBody
    public Page<ProgramDTO> program(@PageableDefault Pageable pageable, String keyword) {
        return programService.getPrograms(pageable, keyword);
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
