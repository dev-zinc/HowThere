package com.howthere.app.controller.program;

import com.howthere.app.domain.Search;
import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.domain.program.ProgramReserveDTO;
import com.howthere.app.domain.rent.RentCarDTO;
import com.howthere.app.domain.rent.RentCarPaymentDTO;
import com.howthere.app.service.program.ProgramReservationService;
import com.howthere.app.service.program.ProgramService;
import com.howthere.app.service.rent.payment.RentCarPaymentService;
import com.howthere.app.service.rent.rentCar.RentCarService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/program")
@RequiredArgsConstructor
@Slf4j
public class ProgramController {

    private final ProgramService programService;
    private final ProgramReservationService programReservationService;
    private final HttpSession session;

    private final RentCarService rentCarService;

    private final RentCarPaymentService rentCarPaymentService;
    // http://localhost:10000/program/list
    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest req, ModelAndView mv,
        @PageableDefault(page = 0, size = 5) Pageable pageable, Search search) {
        // TODO: 2023/08/05 지도 지우고 무한 스크롤로 변경
        mv.setViewName("program/list");
        final Page<ProgramDTO> programs = programService.getProgramsWithThumbnail(
            pageable, search);
        log.info(programs.toString());
        log.info(search.toString());
        mv.addObject("pagination", programs);
        return mv;
    }
    
    // http://localhost:10000/program/detail
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam Long id,HttpServletRequest req, ModelAndView mv) {
        final ProgramDTO programDTO = programService.getProgram(id);
        mv.setViewName("program/detail");
        mv.addObject("program", programDTO);
        mv.addObject("member", session.getAttribute("member"));
        return mv;
    }

    // http://localhost:10000/program/reservation
    @GetMapping("/reservation/{id}")
    public String reservation(@PathVariable Long id, Model model) {
        programReservationService.getReservation(id).ifPresent((reservation) -> {
            model.addAttribute("reservation", reservation);
        });
        return "/program/reservation";
    }

    @PostMapping("/reservation")
    public RedirectView reservation(ProgramReserveDTO programReserveDTO) {
        Long id = programReservationService.reserve(programReserveDTO);
        log.info(id.toString());
        return new RedirectView("/program/reservation/" + id);
    }

    // http://localhost:10000/program/rent
    @GetMapping("/rent")
    public ModelAndView rent(HttpServletRequest req, ModelAndView mv, @PageableDefault(page = 0,size = 6) Pageable pageable, Model model) {
        mv.setViewName("program/rent");
        Slice<RentCarDTO> rentCarDTOS =  rentCarService.getRentCarList(pageable);
        model.addAttribute("rentCars", rentCarDTOS);
        rentCarDTOS.forEach(rentCarDTO -> log.info(rentCarDTO.toString()));
        return mv;
    }


    // TODO: 2023/08/05  렌트카 예약 페이지 구현
    // http://localhost:10000/program/rent/reservation
    @GetMapping("/rent/reservation")
    public ModelAndView rentReservation(HttpServletRequest req, ModelAndView mv, @RequestParam(value = "paymentId",required = false) Long paymentId,@RequestParam(value = "rentCarId",required = false) Long id, Model model) {
        log.info("rentCarId  : {}",id);
        RentCarDTO rentCarDTO = rentCarService.getRentCarById(id);
        model.addAttribute("rentCar", rentCarDTO);
        model.addAttribute("paymentId", paymentId);
        mv.setViewName("program/rent-reservation");
        return mv;
    }




//    =========================================================================================================
    // 테스트 페이지 렌트카 리스트
    // http://localhost:10000/program/rent_test
    @GetMapping("/rent_test")
    public String rentCarListTest(@PageableDefault(page = 0,size = 6) Pageable pageable, Model model){
        Slice<RentCarDTO> rentCarDTOS =  rentCarService.getRentCarList(pageable);
        model.addAttribute("rentCars", rentCarDTOS);
        rentCarDTOS.forEach(rentCarDTO -> log.info(rentCarDTO.toString()));
        return "/test/rent_test_list.html";
    }

    // 테스트 페이지 렌트카 상세보기
    // http://localhost:10000/program/rent_detail_test
    @GetMapping("/rent_detail_test")
    public String rentCarDetailTest(@RequestParam(value = "paymentId",required = false) Long paymentId,@RequestParam(value = "rentCarId",required = false) Long id, Model model) {
        log.info("rentCarId  : {}",id);
        RentCarDTO rentCarDTO = rentCarService.getRentCarById(id);
        model.addAttribute("rentCar", rentCarDTO);
        model.addAttribute("paymentId", paymentId);
        return "/test/rent_test_detail.html";
    }

    // 테스트 페이지 영수증 리스트
    // http://localhost:10000/program/rent_payment_list_test
    @GetMapping("/rent_payment_list_test")
    public String rentCarPaymentListTest(HttpSession session, Model model) {
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        List<RentCarPaymentDTO> rentCarPaymentDTOS = rentCarPaymentService.getRentCarPaymentListByMemberId(memberId);
        model.addAttribute("rentCarPayments", rentCarPaymentDTOS);

        log.info("memberId  : {}",memberId);

        return "/test/rent_test_payment_list.html";
    }

    // 테스트 페이지 영수증 상세보기
    // http://localhost:10000/program/rent_payment_detail_test
    @GetMapping("/rent_payment_detail_test")
    public String rentCarPaymentDetailTest(@RequestParam("rentCarPaymentId") Long id,Model model) {
        RentCarPaymentDTO rentCarPaymentDTO = rentCarPaymentService.getRentCarPaymentById(id).get();
        model.addAttribute("rentCarPayment", rentCarPaymentDTO);

        log.info("id  : {}", id);
        return "/test/rent_payment_test_detail.html";
    }

    @PostMapping("/rent_reservation")
    @ResponseBody
    public Map<String, Object> reserveRentCarTest(@RequestParam(value = "rentCarId",required = false) Long rentCarId, HttpSession session, @RequestParam("startDay") String  startDay,@RequestParam("endDay")  String  endDay) {
        Map<String,Object> result = new HashMap<>();
        Long memberId = ((MemberDTO)session.getAttribute("member")).getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedStartDay = LocalDate.parse(startDay, formatter);
        LocalDate parsedEndDay = LocalDate.parse(endDay, formatter);

        log.info("====================================================");
        log.info(rentCarId.toString());
        log.info(memberId.toString());
        log.info(parsedStartDay.toString());
        log.info(parsedEndDay.toString());
        log.info("====================================================");

        rentCarPaymentService.reserveRentCar(rentCarId, memberId, parsedStartDay, parsedEndDay);
        result.put("response", "success");
        return result;
    }
    // 렌트카 예약 취소 테스트
    @DeleteMapping("/rent_cancel")
    @ResponseBody
    public Map<String, Object> cancelRentCarTest(@RequestParam(value = "paymentId",required = false) Long paymentId) {
        Map<String,Object> result = new HashMap<>();

        rentCarPaymentService.cancelRentCar(paymentId);
        result.put("response", "success");
        return result;
    }

    // 렌트카 예약 수정 테스트
    @PostMapping("/rent_update")
    @ResponseBody
    public Map<String, Object> updateRentCarTest(@RequestParam(value = "paymentId",required = false) Long paymentId,@RequestParam("price") int price, @RequestParam("startDay") String  startDay,@RequestParam("endDay")  String  endDay) {
        Map<String,Object> result = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedStartDay = LocalDate.parse(startDay, formatter);
        LocalDate parsedEndDay = LocalDate.parse(endDay, formatter);

        log.info("paymentId :{}", paymentId);
        log.info("price :{}", price);
        log.info("parsedStartDay :{}", parsedStartDay);
        log.info("parsedEndDay :{}", parsedEndDay);

        rentCarPaymentService.modifyRentCar(paymentId, price, parsedStartDay, parsedEndDay);
        result.put("response", "success");
        return result;
    }

    // 지도 api 테스트
    // http://localhost:10000/program/test_map
    @GetMapping("/test_map")
    public String testMap(@RequestParam(value = "rentCarId",required = false) Long rentCarId, Model model) {
        RentCarDTO rentCarDTO = rentCarService.getRentCarById(rentCarId);
        model.addAttribute("rentCar", rentCarDTO);
        return "/test/test_map.html";
    }
}
