package com.howthere.app.repository.program;

import com.howthere.app.entity.program.ProgramPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramPaymentRepository extends JpaRepository<ProgramPayment, Long> {
}
