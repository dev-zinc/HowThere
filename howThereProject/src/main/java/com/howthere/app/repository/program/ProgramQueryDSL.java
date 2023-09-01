package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.domain.program.ProgramListDTO;
import com.howthere.app.domain.program.ProgramMainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramQueryDSL {
    Page<ProgramListDTO> findAllWithLimit(Pageable pageable, String keyword);

    Page<ProgramDTO> findAllWithThumbnail(Pageable pageable);

    List<ProgramMainDTO> findAll10();
}
