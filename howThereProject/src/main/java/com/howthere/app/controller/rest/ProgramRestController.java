package com.howthere.app.controller.rest;

import com.howthere.app.entity.Program;
import com.howthere.app.service.ProgramRestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/program/api/*")
public class ProgramRestController {
    private ProgramRestService programRestService;

    @GetMapping("list")
    public Page<Program> getPrograms(@PageableDefault Pageable pageable, String keyword) {
        return programRestService.getList(pageable, keyword);
    }

    @GetMapping("remove")
    public void remove(Program program) {
        programRestService.remove(program);
    }
}
