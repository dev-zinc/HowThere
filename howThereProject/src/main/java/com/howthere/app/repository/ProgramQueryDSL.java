package com.howthere.app.repository;

import com.howthere.app.entity.Program;
import com.howthere.app.object.Search;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramQueryDSL {
    List<Program> findAllWithLimit(Pageable pageable, Search search);

}
