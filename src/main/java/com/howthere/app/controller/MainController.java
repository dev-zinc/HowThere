package com.howthere.app.controller;

import com.howthere.app.domain.program.ProgramMainDTO;
import com.howthere.app.service.diary.DiaryService;
import com.howthere.app.service.program.ProgramService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final ProgramService programService;
    private final DiaryService diaryService;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("programs", programService.getPrograms(null));
        model.addAttribute("diaries", diaryService.getList());
        return "index";
    }

    @GetMapping("api/list")
    @ResponseBody
    public List<ProgramMainDTO> getList(@RequestParam String region) {
        List<ProgramMainDTO> programs = programService.getPrograms(region).stream().peek(programMainDTO -> 
            programMainDTO.setFilePath("2023/"+programMainDTO.getFilePath().substring(15)))
                .collect(Collectors.toList());
        
        log.info(programs.toString());
        return programs;
    }
}
