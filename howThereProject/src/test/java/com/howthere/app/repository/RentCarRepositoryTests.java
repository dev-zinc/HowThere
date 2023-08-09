package com.howthere.app.repository;

import com.howthere.app.HowThereApplication;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.RentCarCompanyRepository;
import com.howthere.app.repository.rent.RentCarPaymentRepository;
import com.howthere.app.repository.rent.RentCarRepository;
import com.howthere.app.type.RentCarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Test
    public void rentCarCompany_save(){
        RentCarCompany rentCarCompany = RentCarCompany.builder().rentCarCompanyName("A회사").rentCarCompanyAddress(
                Address.builder().address("서울").addressDetail("강동구").latitude(33.2).longitude(22.1).build()).build();

        rentCarCompanyRepository.save(rentCarCompany);
    }

    @Test
    public void rentCar_save() {
        RentCar rentCar = RentCar.builder()
                .rentCarCompany(rentCarCompanyRepository.findById(2L).get())
                .rentCarName("엑센트")
                .rentCarPrice(100000)
                .rentCarType(RentCarType.CompactCar)
                .build();

        rentCarRepository.save(rentCar);
    }


    @Test
    public void rentCarPayment_save() {
            RentCarPayment rentCarPayment = RentCarPayment.builder()
                    .member(memberRepository.findById(1L).get())
                    .rentCar(rentCarRepository.findById(3L).get())
                    .startDay(LocalDateTime.now())
                    .endDay(LocalDateTime.of(LocalDate.of(2023,9,4) , LocalTime.now()))
                    .carRentTotalPrice(3000000)
                    .build();
            rentCarPaymentRepository.save(rentCarPayment);
    }

    @Test
    public void findRentCarPaymentById() {
        log.info(rentCarPaymentRepository.findById(4L).get().toString());
    }

    @Test
    public void deleted_rentCarPayment_remove() {
        rentCarPaymentRepository.findById(4L).ifPresent(rentCarPaymentRepository::delete);
    }
}
