package com.howthere.app.service.rent;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.service.rent.company.RentCarCompanyService;
import com.howthere.app.service.rent.payment.RentCarPaymentService;
import com.howthere.app.service.rent.rentCar.RentCarService;
import com.howthere.app.type.RentCarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(false)
public class RentCarServiceTests {
    @Autowired
    private RentCarService rentCarService;
    @Autowired
    private RentCarCompanyService rentCarCompanyService;
    @Autowired
    private RentCarPaymentService rentCarPaymentService;


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RentCarRepository rentCarRepository;

    // 렌트카 리스트
    @Test
    public void getRentCarListTest() {
      rentCarService.getRentCarList(PageRequest.of(0, 6)).forEach(rentCar -> log.info(rentCar.toString()));
    }

    // 렌트카 id로 업체 조회
    @Test
    public void getRentCarCompanyByRentCarIdTest() {
        log.info(rentCarCompanyService.getRentCarCompanyByRentCarId(34L).toString());
    }

    // 렌트카 예약
    @Test
    public void reserveRentCarTest() {
        HttpSession session = null;
        Member member = memberRepository.findById(1L).get();
        RentCar rentCar = rentCarRepository.findById(37L).get();
        log.info("=========================== id ===={}",((MemberDTO)session.getAttribute("member")).getId());

    }

    // 렌트카 예약 수정하기
    @Test
    public void modifyRentCarPayment() {
    }

    // 렌트카 영수증 리스트
    @Test
    public void getRentCarPaymentListByMemberIdTest() {
        rentCarPaymentService.getRentCarPaymentListByMemberId(1L);
    }

    // 렌트카 영수증 상세보기
    @Test
    public void getRentCarPaymentByIdTest(){
        log.info("---------------------------=====================---------------------========================----------");
        log.info(rentCarPaymentService.getRentCarPaymentById(21L).get().toString());
    }



    // 렌트카 상세보기
    @Test
    public void getRentCarByIdTest() {
        log.info(rentCarService.getRentCarById(41L).toString());
    }



}
