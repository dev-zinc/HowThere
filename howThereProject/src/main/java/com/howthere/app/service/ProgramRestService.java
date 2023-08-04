package com.howthere.app.service;

import com.howthere.app.entity.Program;
import com.howthere.app.object.Search;
import com.howthere.app.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramRestService implements AdministratorRestService<Program> {
    private ProgramRepository programRepository;

    @Override
    public List<Program> getList(Pageable pageable, Search search) {
        return null;
    }

    @Override
    public void modify(Program program) {

    }

    @Override
    public void remove(Program program) {

    }
}
