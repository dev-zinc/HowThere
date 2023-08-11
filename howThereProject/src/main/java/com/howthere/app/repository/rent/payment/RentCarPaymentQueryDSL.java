package com.howthere.app.repository.rent.payment;

import com.howthere.app.entity.rent.RentCarPayment;

import java.util.List;

public interface RentCarPaymentQueryDSL {
    public List<RentCarPayment> findAllByMemberId_queryDSL(Long memberId);
}
