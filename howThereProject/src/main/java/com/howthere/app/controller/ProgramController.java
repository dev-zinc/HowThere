package com.howthere.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/program")
public class ProgramController {

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/list");
        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView detail(HttpServletRequest req, ModelAndView mv) {
        mv.setViewName("program/detail");
        return mv;
    }
}
