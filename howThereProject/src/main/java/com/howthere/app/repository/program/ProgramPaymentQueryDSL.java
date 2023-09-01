package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramPaymentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramPaymentQueryDSL {

    Page<ProgramPaymentDTO> getPaymentListByMemberId(Pageable pageable, Long memberId);
}
