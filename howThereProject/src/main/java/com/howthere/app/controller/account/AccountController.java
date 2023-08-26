package com.howthere.app.controller.account;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.entity.member.Member;
import com.howthere.app.service.account.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void payment() {
        ;
    }

    //http://localhost:10000/account/diary
    @GetMapping("diary")
    public void diary() {
        ;
    }

    //http://localhost:10000/account/program
    @GetMapping("program")
    public void program() {
        ;
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
