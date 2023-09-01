package com.howthere.app.controller;

import com.howthere.app.service.diary.DiaryService;
import com.howthere.app.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final ProgramService programService;
    private final DiaryService diaryService;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("programs", programService.getPrograms());
        model.addAttribute("diaries", diaryService.getList());
        return "index";
    }
}
