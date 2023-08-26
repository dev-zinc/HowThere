package com.howthere.app.service;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.service.rent.RentCarCompanyService;
import com.howthere.app.service.rent.RentCarPaymentService;
import com.howthere.app.service.rent.RentCarService;
import com.howthere.app.type.RentCarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

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
      rentCarService.getRentCarList(PageRequest.of(0, 6), RentCarType.CompactCar).forEach(rentCar -> log.info(rentCar.toString()));
    }

    // 렌트카 id로 업체 조회
    @Test
    public void getRentCarCompanyByRentCarIdTest() {
        log.info(rentCarCompanyService.getRentCarCompanyByRentCarId(34L).toString());
    }

    // 렌트카 예약
    @Test
    public void reserveRentCarTest() {
        Member member = memberRepository.findById(1L).get();
        RentCar rentCar = rentCarRepository.findById(37L).get();

        RentCarPayment rentCarPayment = RentCarPayment.builder()
                                        .member(member).rentCar(rentCar)
                                        .startDay(LocalDateTime.now())
                                        .endDay(LocalDateTime.of(LocalDate.of(2023,9,11) , LocalTime.now()))
                                        .carRentTotalPrice(100000).build();
        
        rentCarPaymentService.reserveRentCar(rentCarPayment);
    }

    // 렌트카 예약 리스트 확인
    @Test
    public void getRentCarPaymentListByMemberId() {
        rentCarPaymentService.getRentCarPaymentListByMemberId(1L).forEach(rentCarPayment -> log.info(rentCarPayment.toString()));
    }

    // 렌트카 예약 확인
    @Test
    public void getRentCarPaymentByIdTest() {
        log.info(rentCarPaymentService.getRentCarPaymentById(49L).toString());
    }





}
