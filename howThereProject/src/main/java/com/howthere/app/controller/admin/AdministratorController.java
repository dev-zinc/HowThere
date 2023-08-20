package com.howthere.app.controller.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.AnswerDTO;
import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.entity.member.Member;
import com.howthere.app.service.admin.AnnouncementService;
import com.howthere.app.service.admin.AnswerService;
import com.howthere.app.service.admin.QuestionService;
import com.howthere.app.service.house.HouseService;
import com.howthere.app.service.member.MemberService;
import com.howthere.app.service.program.ProgramReservationService;
import com.howthere.app.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String houseDetail() {
        return "/house/detail";
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

    //===============================================================REST

    @GetMapping("api/inquiry")
    @ResponseBody
    public Page<QuestionDTO> inquiry(@PageableDefault Pageable pageable, String keyword) {
        return questionService.getQuestions(pageable, keyword);
    }

    @GetMapping("api/program")
    @ResponseBody
    public Page<ProgramDTO> program(@PageableDefault Pageable pageable, String keyword) {
        return programService.getPrograms(pageable, keyword);
    }

    @GetMapping("api/notice")
    @ResponseBody
    public Page<AnnouncementDTO> notice(@PageableDefault Pageable pageable, String keyword) {
        return announcementService.getAnnouncementList(pageable, keyword);
    }

    @GetMapping("api/member")
    @ResponseBody
    public Page<Member> member(@PageableDefault Pageable pageable, String keyword) {
        return memberService.getMembers(pageable, keyword);
    }

    @GetMapping("api/house")
    @ResponseBody
    public Page<HouseDTO> house(@PageableDefault Pageable pageable, String keyword) {
        return houseService.getHouses(pageable, keyword);
    }

    @GetMapping("api/reservation")
    @ResponseBody
    public Page<ProgramReservationDTO> reserve(@PageableDefault Pageable pageable, String keyword) {
        return programReservationService.getProgramReservations(pageable, keyword);
    }
}
