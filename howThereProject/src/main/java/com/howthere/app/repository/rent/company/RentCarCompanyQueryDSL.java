package com.howthere.app.repository.rent.company;

import com.howthere.app.entity.rent.RentCarCompany;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.Optional;

public interface RentCarCompanyQueryDSL {

    public Optional<RentCarCompany> findOneByRentCarId(Long rentCarId);
}
