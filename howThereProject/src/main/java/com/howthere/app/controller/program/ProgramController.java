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
}
