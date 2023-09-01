package com.howthere.app.controller.program;

import com.howthere.app.domain.Search;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.service.program.ProgramService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    // http://localhost:10000/program/list
    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest req, ModelAndView mv,
        @PageableDefault(page = 0, size = 5) Pageable pageable, Search search) {
        // TODO: 2023/08/05 지도 지우고 무한 스크롤로 변경
        log.info(search.toString());
        mv.setViewName("program/list");
        final Page<ProgramDTO> programs = programService.getProgramsWithThumbnail(
            pageable, search);
        mv.addObject("pagination", programs);
        return mv;
    }

    // http://localhost:10000/program/detail
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Long id,HttpServletRequest req, ModelAndView mv) {
        final ProgramDTO programDTO = programService.getProgram(id);
        mv.setViewName("program/detail");
        mv.addObject("program", programDTO);
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
