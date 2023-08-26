package com.howthere.app.controller.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.domain.member.MemberInfoDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/administrator/api/*")
public class AdministratorRestController {
    private final ProgramService programService;
    private final MemberService memberService;
    private final AnnouncementService announcementService;
    private final QuestionService questionService;
    private final HouseService houseService;
    private final ProgramReservationService programReservationService;
    private final AnswerService answerService;

    @GetMapping("inquiry")
    public Page<QuestionDTO> getInquiries(@PageableDefault Pageable pageable, String keyword) {
        return questionService.getQuestions(pageable, keyword);
    }

    @GetMapping("program")
    public Page<ProgramDTO> getPrograms(@PageableDefault Pageable pageable, String keyword) {
        return programService.getPrograms(pageable, keyword);
    }

    @PostMapping("program/modify")
    public void modifyPrograms(@RequestBody List<Long> ids) {
        programService.modifyAllBy(ids);
    }

    @GetMapping("notice")
    public Page<AnnouncementDTO> getNotices(@PageableDefault Pageable pageable, String keyword) {
        return announcementService.getAnnouncementList(pageable, keyword);
    }

    @GetMapping("member")
    public Page<MemberInfoDTO> getMembers(@PageableDefault Pageable pageable, String keyword) {
        return memberService.getMembers(pageable, keyword);
    }

    @PostMapping("member/modify")
    public void modifyMembers(@RequestBody List<Long> ids) {
        memberService.modifyAllActivationById(ids);
    }

    @GetMapping("house")
    public Page<HouseDTO> getHouses(@PageableDefault Pageable pageable, String keyword) {
        return houseService.getHouses(pageable, keyword);
    }

    @PostMapping("house/delete")
    public void deleteHouses(@RequestBody List<Long> ids) {
        houseService.deleteAllBy(ids);
    }

    @GetMapping("reservation")
    public Page<ProgramReservationDTO> getProgramReservations(@PageableDefault Pageable pageable, String keyword) {
        return programReservationService.getProgramReservations(pageable, keyword);
    }

    @PostMapping("reservation/modify")
    public void getProgramReservations(@RequestBody List<Long> ids) {
        programReservationService.changeAllVerifiedState(ids);
    }
}
