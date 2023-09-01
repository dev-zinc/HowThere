package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.entity.program.ProgramPayment;

public interface ProgramPaymentService {

    ProgramPayment save(ProgramReservationDTO reservationDTO);

}
