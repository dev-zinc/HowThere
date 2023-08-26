package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramReservationService {
    Page<ProgramReservationDTO> getProgramReservations(Pageable pageable, String keyword);
    void changeAllVerifiedState(List<Long> ids);
}
