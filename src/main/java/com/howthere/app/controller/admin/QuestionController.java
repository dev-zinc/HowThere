package com.howthere.app.controller.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.service.admin.QuestionService;
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

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/one_to_one_question/*")
public class QuestionController {

    private final QuestionService questionService;

    // http://localhost:10000/one_to_one_question/one_to_one_question
    @GetMapping("one_to_one_question")
    public void question() {;}

    @PostMapping("write")
    public String write(QuestionDTO dto){
        Long id = questionService.questionSave(dto);
        return "redirect:/one_to_one_question/detail_one_to_one?id=" + id;
    }

    // http://localhost:10000/one_to_one_question/question_list
    @GetMapping("question_list")
    public void questionList(@PageableDefault Pageable pageable, Model model, HttpSession session) {
        Page<QuestionDTO> myQuestions = questionService.getMyQuestions(pageable, session);
        model.addAttribute("page", myQuestions);
    }

    // http://localhost:10000/one_to_one_question/detail_one_to_one
    @GetMapping("detail_one_to_one")
    public void questionDetail(Long id, Model model) {
        model.addAttribute("qna", questionService.findQnAByQuestionId(id));
    }


}
