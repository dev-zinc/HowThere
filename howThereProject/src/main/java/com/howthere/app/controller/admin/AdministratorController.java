package com.howthere.app.controller.admin;

import com.howthere.app.domain.admin.AnswerDTO;
import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.service.admin.AnnouncementService;
import com.howthere.app.service.admin.AnswerService;
import com.howthere.app.service.admin.QuestionService;
import com.howthere.app.service.house.HouseService;
import com.howthere.app.service.member.MemberService;
import com.howthere.app.service.program.ProgramReservationService;
import com.howthere.app.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/administrator/*")
public class AdministratorController {
    private final ProgramService programService;
    private final MemberService memberService;
    private final AnnouncementService announcementService;
    private final QuestionService questionService;
    private final HouseService houseService;
    private final ProgramReservationService programReservationService;
    private final AnswerService answerService;

    //http://localhost:10000/administrator/program
    @GetMapping("program")
    public void program() {;}

    //http://localhost:10000/administrator/notice/detail
    @GetMapping("program/detail")
    public String programDetail() {
        return "/program/detail";
    }

    //http://localhost:10000/administrator/stay
    @GetMapping("house")
    public void house() {;}

    //http://localhost:10000/administrator/notice/detail
    @GetMapping("house/detail")
    public String houseDetail(Long id, Model model) {
        model.addAttribute("id", id);
        return "/host/write";
    }

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
    public String inquiryDetail(Long id, Model model) {
        QuestionDTO questionDTO = questionService.findQuestion(id);
        model.addAttribute("inquiry", questionDTO);
        model.addAttribute("answer", new AnswerDTO());
        return "/administrator/inquiry-detail";
    }

    @PostMapping("inquiry/write")
    public RedirectView save(AnswerDTO answerDTO) {
        log.info(answerDTO.toString());
        answerService.save(answerDTO);
        return new RedirectView("");
    }

    @PostMapping("inquiry/modify")
    public RedirectView modify(AnswerDTO answerDTO) {
        log.info(answerDTO.toString());
        answerService.modify(answerDTO);
        return new RedirectView("");
    }
}
