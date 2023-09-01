package com.howthere.app.controller.account;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.domain.program.ProgramPaymentDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.service.account.AccountService;
import com.howthere.app.service.program.ProgramPaymentService;
import com.howthere.app.service.program.ProgramReservationService;
import com.howthere.app.type.RentCarType;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/account/*")
public class AccountController {

    private final AccountService accountService;
    private final ProgramReservationService reservationService;
    private final ProgramPaymentService paymentService;

    //http://localhost:10000/account/menu
    @GetMapping("menu")
    public void menu() {
        ;
    }

    //http://localhost:10000/account/setting
    @GetMapping("setting")
    public void setting(HttpSession session, Model model) {
        final MemberDTO member = (MemberDTO) session.getAttribute("member");
        model.addAttribute("member", member);
    }

    //http://localhost:10000/account/payment
    @GetMapping("payment")
    public void payment(HttpSession session,
        @PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {
        final MemberDTO member = (MemberDTO) session.getAttribute("member");
        final Page<ProgramPaymentDTO> pagination = paymentService.getPaymentListByMemberId(
            pageable, member.getId());
        model.addAttribute("pagination", pagination);
    }

    @PostMapping("payment")
    public void reservationProgram(@RequestBody ProgramReservationDTO reservationDTO) {
        paymentService.save(reservationDTO);

        reservationService.updateReservation(reservationDTO);
    }

    //http://localhost:10000/account/diary
    @GetMapping("diary")
    public void diary() {
        ;
    }

    //http://localhost:10000/account/program
    @GetMapping("program")
    public void program(@PageableDefault(page = 0, size = 9) Pageable pageable,
        HttpSession session, Model model) {
        final MemberDTO member = (MemberDTO) session.getAttribute("member");
        final Page<ProgramReservationDTO> pagination = reservationService.getReservationByMemberId(
            pageable, member.getId());
        model.addAttribute("pagination", pagination);
    }

    @PostMapping("update")
    @ResponseBody
    public String updateMyInfo(HttpSession session, @RequestParam String name) {
//        final String name = dto.getMemberName();
        final MemberDTO member = (MemberDTO) session.getAttribute("member");
        final MemberDTO memberDTO = accountService.updateMyInfo(member.getId(), name);
        session.setAttribute("member", memberDTO);
        return memberDTO.getMemberName();
    }
}
