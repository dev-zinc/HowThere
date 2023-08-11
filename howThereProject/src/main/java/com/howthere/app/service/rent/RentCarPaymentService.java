package com.howthere.app.service.rent;

import com.howthere.app.domain.rent.RentCarPaymentDTO;
import com.howthere.app.entity.rent.RentCarPayment;

import java.util.List;
import java.util.Optional;

public interface RentCarPaymentService {

    // 렌트카 예약
    public void reserveRentCar(RentCarPayment rentCarPayment);

    //렌트카 예약 리스트
    public List<RentCarPayment> getRentCarPaymentListByMemberId(Long memberId);

    // 렌트카 예약 확인
    public Optional<RentCarPayment> getRentCarPaymentById(Long id);
}
