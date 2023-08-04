package com.howthere.app.repository;

import com.howthere.app.entity.rentCar.RentCarCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarCompanyRepository extends JpaRepository<RentCarCompany, Long> {
}
