package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramQueryDSL {
    Page<ProgramDTO> findAllWithLimit(Pageable pageable, String keyword);

    Page<ProgramDTO> findAllWithThumbnail(Pageable pageable);
}
