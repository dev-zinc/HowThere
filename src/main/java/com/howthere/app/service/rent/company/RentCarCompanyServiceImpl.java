package com.howthere.app.service.rent.company;

import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.repository.rent.company.RentCarCompanyRepository;
import com.howthere.app.service.rent.company.RentCarCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentCarCompanyServiceImpl implements RentCarCompanyService {
    private final RentCarCompanyRepository rentCarCompanyRepository;

    // 렌트카 업체 정보 갖고 오기
    @Override
    public Optional<RentCarCompany> getRentCarCompanyByRentCarId(Long RentCarId) {
        return rentCarCompanyRepository.findOneByRentCarId(RentCarId);
    }

}
