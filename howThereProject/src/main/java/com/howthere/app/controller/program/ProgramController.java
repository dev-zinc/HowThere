package com.howthere.app.controller.program;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.service.program.ProgramService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/program")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    // http://localhost:10000/program/list
    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest req, ModelAndView mv,
        @PageableDefault(page = 0, size = 5) Pageable pageable) {
        // TODO: 2023/08/05 지도 지우고 무한 스크롤로 변경
        mv.setViewName("program/list");
        final Page<ProgramDTO> programs = programService.getProgramsWithThumbnail(
            pageable);
        mv.addObject("pagination", programs);
        return mv;
    }

    // http://localhost:10000/program/detail
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Long id, HttpSession session, ModelAndView mv) {
        final ProgramDTO programDTO = programService.getProgram(id);
        final MemberDTO member = (MemberDTO) session.getAttribute("member");
        mv.setViewName("program/detail");
        mv.addObject("program", programDTO);
        mv.addObject("myInfo", member);
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
