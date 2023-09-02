package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramPaymentDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.entity.program.ProgramPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramPaymentService {

    ProgramPayment save(ProgramReservationDTO reservationDTO);

    Page<ProgramPaymentDTO> getPaymentListByMemberId(Pageable pageable, Long id);
}
