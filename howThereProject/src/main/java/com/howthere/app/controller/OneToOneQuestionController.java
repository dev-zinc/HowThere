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
    // http://localhost:10000/one_to_one_question/one_to_one_question
    @GetMapping("one_to_one_question")
    public void question() {;}

    // http://localhost:10000/one_to_one_question/question_list
    @GetMapping("question_list")
    public void questionList() {;}

    // http://localhost:10000/one_to_one_question/detail_one_to_one
    @GetMapping("detail_one_to_one")
    public void questionDetail() {;}
}
