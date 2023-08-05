package com.howthere.app.repository.rent;

import com.howthere.app.entity.rent.RentCarPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarPaymentRepository extends JpaRepository<RentCarPayment, Long> {
}
