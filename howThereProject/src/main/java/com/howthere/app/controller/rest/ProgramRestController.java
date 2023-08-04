package com.howthere.app.controller.rest;

import com.howthere.app.entity.Program;
import com.howthere.app.object.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/program/api/*")
public class ProgramRestController {
    @GetMapping("list")
    public List<Program> getPrograms(@PageableDefault Pageable pageable, Search search) {
        return null;
    }
}
