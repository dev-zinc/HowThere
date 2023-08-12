package com.howthere.app.controller.program;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/program")
public class ProgramController {

    // http://localhost:10000/program/list
    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest req, ModelAndView mv) {
        // TODO: 2023/08/05 지도 지우고 무한 스크롤로 변경
        mv.setViewName("program/list");
        return mv;
    }

    // http://localhost:10000/program/detail
    @GetMapping("/detail")
    public ModelAndView detail(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/detail");
        return mv;
    }

    // http://localhost:10000/program/reservation
    @GetMapping("/reservation")
    public ModelAndView reservation(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/reservation");
        return mv;
    }

    // http://localhost:10000/program/rent
    @GetMapping("/rent")
    public ModelAndView rent(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/rent");
        return mv;
    }

    // TODO: 2023/08/05  렌트카 예약 페이지 구현
    @GetMapping("/rent/reservation")
    public ModelAndView rentReservation(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/rent-reservation");
        return mv;
    }
}
