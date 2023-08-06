package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.program.Program;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.program.QProgram.program;

@RequiredArgsConstructor
public class ProgramQueryDSLImpl implements ProgramQueryDSL {
    private JPAQueryFactory query;

    @Override
    public Page<ProgramDTO> findAllWithLimit(Pageable pageable, String keyword) {
        final List<ProgramDTO> programDTOs = query.select(
                Projections.fields(ProgramDTO.class,
                    program.id
                )
        ).from(program).where(program.programName.contains(keyword)).fetch();
        Long count = query.select(program.count()).from(program).fetchOne();
        return new PageImpl<>(programDTOs, pageable, count == null ? 0 : count);
    }
}
