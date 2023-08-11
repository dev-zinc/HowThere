package com.howthere.app.repository.rent.payment;

import com.howthere.app.entity.rent.RentCarPayment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.howthere.app.entity.rent.QRentCarPayment.rentCarPayment;

@RequiredArgsConstructor
public class RentCarPaymentQueryDSLImpl implements RentCarPaymentQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public List<RentCarPayment> findAllByMemberId_queryDSL(Long memberId) {
        return query.selectFrom(rentCarPayment)
                .where(rentCarPayment.member.id.eq(memberId)).fetch();
    }
}
