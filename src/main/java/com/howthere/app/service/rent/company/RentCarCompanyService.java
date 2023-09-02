package com.howthere.app.service.rent.company;

import com.howthere.app.entity.rent.RentCarCompany;

import java.util.Optional;

public interface RentCarCompanyService {
    // 렌트카 업체 정보 갖고 오기
    public Optional<RentCarCompany> getRentCarCompanyByRentCarId(Long RentCarId);
}
