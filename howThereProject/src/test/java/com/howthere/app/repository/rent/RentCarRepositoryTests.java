package com.howthere.app.repository.rent;

import com.howthere.app.HowThereApplication;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.company.RentCarCompanyRepository;
import com.howthere.app.repository.rent.payment.RentCarPaymentRepository;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.type.RentCarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(false)
public class RentCarRepositoryTests {
    @Autowired
    private RentCarCompanyRepository rentCarCompanyRepository;
    @Autowired
    private RentCarRepository rentCarRepository;
    @Autowired
    private RentCarPaymentRepository rentCarPaymentRepository;
    @Autowired
    private MemberRepository memberRepository;

    // 렌트카 회사 등록
    @Test
    public void rentCarCompany_save_test(){
        RentCarCompany rentCarCompany1 = RentCarCompany.builder()
                .rentCarCompanyName("정도인터내셔널㈜")
                .rentCarCompanyAddress(
                Address.builder()
                        .address("강원도 원주시")
                        .addressDetail("소초면 장양리 54")
                        .latitude(37.42104097).longitude(127.97533700)
                        .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany1);

        RentCarCompany rentCarCompany2 = RentCarCompany.builder()
                .rentCarCompanyName("현대디씨렌트카㈜")
                .rentCarCompanyAddress(
                Address.builder()
                        .address("강원도 철원군")
                        .addressDetail("동송읍 이평리 840-88")
                        .latitude(38.20662719).longitude(127.2173654)
                        .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany2);

        RentCarCompany rentCarCompany3 = RentCarCompany.builder()
                .rentCarCompanyName("로또렌트카㈜")
                .rentCarCompanyAddress(
                Address.builder()
                        .address("강원도 철원군 ")
                        .addressDetail("갈말읍 군탄리 961-3")
                        .latitude(38.14821979).longitude(127.3042103)
                        .build())
                .build();

        rentCarCompanyRepository.save(rentCarCompany3);
    }

    // 렌트카 등록
    @Test
    public void rentCar_save_test() {
        for (int i=6; i<15; i++){
            RentCar rentCar = RentCar.builder()
                    .rentCarCompany(rentCarCompanyRepository.findById(1L).get())
                    .rentCarName("아반떼")
                    .rentCarPrice(100000)
                    .rentCarType(RentCarType.CompactCar)
                    .build();
            rentCarRepository.save(rentCar);
        }

        for (int i=0; i<6; i++){
            RentCar rentCar = RentCar.builder()
                    .rentCarCompany(rentCarCompanyRepository.findById(2L).get())
                    .rentCarName("소나타")
                    .rentCarPrice(150000)
                    .rentCarType(RentCarType.mediumSizedCar)
                    .build();
            rentCarRepository.save(rentCar);
        }

        for (int i=6; i<15; i++){
            RentCar rentCar = RentCar.builder()
                    .rentCarCompany(rentCarCompanyRepository.findById(3L).get())
                    .rentCarName("산타페")
                    .rentCarPrice(200000)
                    .rentCarType(RentCarType.SUV)
                    .build();
            rentCarRepository.save(rentCar);
        }

    }

    // 렌트카 예약
    @Test
    public void rentCarPayment_save_test() {
    }

    // 렌트카 조회
    @Test
    public void findRentCarPaymentById_test() {
        log.info(rentCarPaymentRepository.findById(4L).get().toString());
    }

    // 렌트카 취소
    @Test
    public void deleted_rentCarPayment_remove_test() {
        rentCarPaymentRepository.findById(4L).ifPresent(rentCarPaymentRepository::delete);
    }

    // 렌트카 예약 확인 리스트
    @Test
    public void findAllByMemberIdTest(){
        log.info("---------------------------=====================---------------------========================----------");
        rentCarPaymentRepository.findAllByMemberId_queryDSL(2L).forEach(rentCarPayment -> log.info(rentCarPayment.toString()));
    }
    // 렌트카 예약 확인
    @Test
    public void findOneByIdTest(){
        log.info("---------------------------=====================---------------------========================----------");
        log.info(rentCarPaymentRepository.findOneById_queryDSL(21L).get().toString());
    }

    // 렌트카 예약 수정
    @Test
    public void updateRentCarPaymentByRentCar(){
    }

    // 렌트카 클릭시 렌트카와 렌트카 지도 정보
    @Test
    public void findOneByRentCarIdTest() {
        log.info(rentCarCompanyRepository.findOneByRentCarId(34L).toString());
    }

//========================================================================================================================================
    // 렌트카 리스트
    @Test
    public void findAllRentCarTest() {
        final Slice<RentCar> rentCarPage = rentCarRepository.findAllWithSlice(PageRequest.of(0, 6),RentCarType.SUV);
        rentCarPage.forEach(rentCar -> log.info(rentCar.getRentCarCompany().toString()));
    }

    // 렌트카 상세정보
    @Test
    public void findOneById() {
        log.info(rentCarRepository.findOneById_dsl(41L).get().toString());
    }
}
