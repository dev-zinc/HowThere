package com.howthere.app.repository.rent.company;

import com.howthere.app.entity.rent.QRentCar;
import com.howthere.app.entity.rent.QRentCarCompany;
import com.howthere.app.entity.rent.RentCarCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static com.howthere.app.entity.rent.QRentCar.*;
import static com.howthere.app.entity.rent.QRentCarCompany.*;

@RequiredArgsConstructor
public class RentCarCompanyQueryDSLImpl implements RentCarCompanyQueryDSL {
    private final JPAQueryFactory query;


    @Override
    public Optional<RentCarCompany> findOneByRentCarId(Long rentCarId) {
        return Optional.ofNullable(query.select(rentCarCompany)
                .from(rentCarCompany)
                .join(rentCar).on(rentCarCompany.id.eq(rentCar.rentCarCompany.id))
                .where(rentCar.id.eq(rentCarId))
                .fetchOne());
    }
}
