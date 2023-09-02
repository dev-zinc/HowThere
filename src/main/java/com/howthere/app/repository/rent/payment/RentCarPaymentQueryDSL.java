package com.howthere.app.repository.rent.payment;

import com.howthere.app.entity.rent.RentCarPayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentCarPaymentQueryDSL {
    // 영수증 리스트
    public List<RentCarPayment> findAllByMemberId_queryDSL(Long memberId);

    // 영수증 상세보기
    public Optional<RentCarPayment> findOneById_queryDSL(Long id);

    // 영수증 수정하기
    public void updateRentCarPaymentById_queryDSL(Long id, Integer totalPrice, LocalDate startDay, LocalDate  endDay);
}
