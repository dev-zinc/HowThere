package com.howthere.app.repository.program;

import com.howthere.app.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramQueryDSL {
    Page<Program> findAllWithLimit(Pageable pageable, String keyword);

}
