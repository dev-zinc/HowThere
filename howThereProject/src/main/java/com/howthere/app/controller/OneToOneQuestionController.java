package com.howthere.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/one_to_one_question/*")
public class OneToOneQuestionController {
    @GetMapping("one_to_one_question")
    public void question() {;}

    @GetMapping("question_list")
    public void questionList() {;}

    @GetMapping("detail_one_to_one")
    public void questionDetail() {;}
}
