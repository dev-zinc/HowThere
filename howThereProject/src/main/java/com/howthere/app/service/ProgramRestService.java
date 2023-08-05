package com.howthere.app.service;

import com.howthere.app.entity.Program;
import com.howthere.app.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
//@Qualifier(value = "program")
public class ProgramRestService implements RestService<Program> {
    private ProgramRepository programRepository;

    @Override
    public Page<Program> getList(Pageable pageable, String keyword) {
        return programRepository.findAllWithLimit(pageable, keyword);
    }

    @Override
    public void modify(Program program) {
        //TODO update
    }

    @Override
    public void remove(Program program) {
        programRepository.delete(program);
    }
}
