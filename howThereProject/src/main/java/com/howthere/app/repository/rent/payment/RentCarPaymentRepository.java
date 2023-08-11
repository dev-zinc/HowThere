package com.howthere.app.repository.rent.payment;

import com.howthere.app.entity.rent.RentCarPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarPaymentRepository extends JpaRepository<RentCarPayment, Long>,RentCarPaymentQueryDSL{
}
