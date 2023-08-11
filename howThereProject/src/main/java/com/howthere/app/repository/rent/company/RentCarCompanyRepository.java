package com.howthere.app.repository.rent.company;

import com.howthere.app.entity.rent.RentCarCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarCompanyRepository extends JpaRepository<RentCarCompany, Long>, RentCarCompanyQueryDSL {
}
