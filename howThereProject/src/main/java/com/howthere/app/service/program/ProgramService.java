package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService {
    Page<ProgramDTO> getPrograms(Pageable pageable, String keyword);
}
