package com.howthere.app.repository.program;

import com.howthere.app.entity.Program;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.QProgram.program;

@RequiredArgsConstructor
public class ProgramQueryDSLImpl implements ProgramQueryDSL {
    private JPAQueryFactory query;

    @Override
    public Page<Program> findAllWithLimit(Pageable pageable, String keyword) {
        final List<Program> programs = query.select(program).from(program).where(program.programName.contains(keyword)).fetch();
        Long count = query.select(program.count()).from(program).fetchOne();
        return new PageImpl<>(programs, pageable, count == null ? 0 : count);
    }
}
