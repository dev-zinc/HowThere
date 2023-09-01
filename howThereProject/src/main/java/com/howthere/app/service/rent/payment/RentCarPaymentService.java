package com.howthere.app.service.rent.payment;

import com.howthere.app.domain.rent.RentCarPaymentDTO;
import com.howthere.app.entity.rent.RentCarPayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentCarPaymentService {

    // 렌트카 예약
    public void reserveRentCar(Long rentCarId, Long memberId , LocalDate startDay, LocalDate endDay);

    //렌트카 예약 리스트
    public List<RentCarPaymentDTO> getRentCarPaymentListByMemberId(Long memberId);

    // 렌트카 예약 확인
    public Optional<RentCarPaymentDTO> getRentCarPaymentById(Long id);

    // 렌트카 예약 취소
    public void cancelRentCar(Long id);

    // 렌트카 예약 수정하기
    public void modifyRentCar(Long id ,Integer price, LocalDate startDay,LocalDate endDay);

    public default RentCarPaymentDTO RentCarPaymentToDTO(RentCarPayment rentCarPayment){
        return RentCarPaymentDTO.builder()
                .id(rentCarPayment.getId())
                .startDay(rentCarPayment.getStartDay())
                .endDay(rentCarPayment.getEndDay())
                .carRentTotalPrice(rentCarPayment.getCarRentTotalPrice())
                .member(rentCarPayment.getMember())
                .rentCar(rentCarPayment.getRentCar())
                .build();
    }
}
