package com.howthere.app.repository.rent.payment;

import com.howthere.app.entity.file.QRentCarFile;
import com.howthere.app.entity.member.QMember;
import com.howthere.app.entity.rent.QRentCar;
import com.howthere.app.entity.rent.QRentCarCompany;
import com.howthere.app.entity.rent.RentCarPayment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.howthere.app.entity.file.QRentCarFile.*;
import static com.howthere.app.entity.member.QMember.*;
import static com.howthere.app.entity.rent.QRentCar.*;
import static com.howthere.app.entity.rent.QRentCarCompany.*;
import static com.howthere.app.entity.rent.QRentCarPayment.rentCarPayment;

@RequiredArgsConstructor
public class RentCarPaymentQueryDSLImpl implements RentCarPaymentQueryDSL {
    private final JPAQueryFactory query;
    // 영수증 리스트
    @Override
    public List<RentCarPayment> findAllByMemberId_queryDSL(Long memberId) {
        return query.selectFrom(rentCarPayment)
                .join(rentCarPayment.member , member).fetchJoin()
                .join(rentCarPayment.rentCar, rentCar).fetchJoin()
                .leftJoin(rentCar.rentCarCompany, rentCarCompany).fetchJoin()
                .leftJoin(rentCar.rentCarFiles, rentCarFile).fetchJoin()
                .where(rentCarPayment.member.id.eq(memberId)).fetch();
    }
    // 영수증 상세보기
    @Override
    public Optional<RentCarPayment> findOneById_queryDSL(Long id) {
        return Optional.ofNullable(query.selectFrom(rentCarPayment)
                .join(rentCarPayment.member , member).fetchJoin()
                .join(rentCarPayment.rentCar, rentCar).fetchJoin()
                .leftJoin(rentCar.rentCarCompany, rentCarCompany).fetchJoin()
                .leftJoin(rentCar.rentCarFiles, rentCarFile).fetchJoin()
                .where(rentCarPayment.id.eq(id)).fetchOne());
    }

    // 영수증 수정하기
    @Transactional
    @Override
    public void updateRentCarPaymentById_queryDSL(Long id, Integer totalPrice, LocalDate startDay, LocalDate endDay) {
        query.update(rentCarPayment)
                .where(rentCarPayment.id.eq(id))
                .set(rentCarPayment.carRentTotalPrice, totalPrice)
                .set(rentCarPayment.startDay, startDay)
                .set(rentCarPayment.endDay, endDay)
                .execute();
    }
}
