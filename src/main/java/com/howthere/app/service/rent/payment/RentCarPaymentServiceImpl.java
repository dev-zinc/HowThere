package com.howthere.app.service.rent.payment;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.domain.rent.RentCarPaymentDTO;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.rent.payment.RentCarPaymentRepository;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.service.member.MemberService;
import com.howthere.app.service.rent.payment.RentCarPaymentService;
import com.howthere.app.service.rent.rentCar.RentCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentCarPaymentServiceImpl implements RentCarPaymentService {
    private final RentCarPaymentRepository rentCarPaymentRepository;
    private final RentCarRepository rentCarRepository;
    private final MemberRepository memberRepository;

    // 렌트카 예약
    @Override
    public void reserveRentCar(Long rentCarId, Long memberId, LocalDate startDay, LocalDate endDay) {
        RentCar rentCar = rentCarRepository.findOneById_dsl(rentCarId).get();
        Member member =memberRepository.findById(memberId).get();

        long rentalDays = ChronoUnit.DAYS.between(startDay, endDay) + 1;

        Integer totalPrice = Math.toIntExact(rentCar.getRentCarPrice() * rentalDays);

        RentCarPayment rentCarPayment = RentCarPayment.builder()
                                        .startDay(startDay)
                                        .endDay(endDay)
                                        .member(member)
                                        .rentCar(rentCar)
                                        .carRentTotalPrice(totalPrice)
                                        .build();

        rentCarPaymentRepository.save(rentCarPayment);
    }
    //렌트카 예약 리스트
    @Override
    public List<RentCarPaymentDTO> getRentCarPaymentListByMemberId(Long memberId) {
        return rentCarPaymentRepository
                .findAllByMemberId_queryDSL(memberId)
                .stream().map(this::RentCarPaymentToDTO)
                .collect(Collectors.toList());

    }

    // 렌트카 예약 상세보기
    @Override
    public Optional<RentCarPaymentDTO> getRentCarPaymentById(Long id) {
        return Optional.ofNullable(RentCarPaymentToDTO(rentCarPaymentRepository.findOneById_queryDSL(id).get()));
    }

    // 렌트카 예약 취소
    @Override
    public void cancelRentCar(Long id) {
        RentCarPayment rentCarPayment = rentCarPaymentRepository.findOneById_queryDSL(id).get();
        rentCarPaymentRepository.delete(rentCarPayment);

    }

    // 렌트카 예약 수정
    @Override
    public void modifyRentCar(Long id,Integer price, LocalDate startDay, LocalDate endDay) {
        long rentalDays = ChronoUnit.DAYS.between(startDay, endDay) + 1;

        Integer totalPrice = Math.toIntExact(price * rentalDays);
        rentCarPaymentRepository.updateRentCarPaymentById_queryDSL(id, totalPrice, startDay, endDay);
    }
}
