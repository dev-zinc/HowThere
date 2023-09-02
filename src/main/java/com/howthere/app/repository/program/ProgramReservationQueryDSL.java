package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramReservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramReservationQueryDSL {
    Page<ProgramReservationDTO> findAllWithKeyword(Pageable pageable, String keyword);

    Page<ProgramReservationDTO> getReservationByMemberId(Pageable pageable, Long id);
}
