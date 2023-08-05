package com.howthere.app.repository.program;

import com.howthere.app.config.QueryDSLConfiguration;
import com.howthere.app.domain.program.ProgramDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.program.QProgram.program;

@RequiredArgsConstructor
public class ProgramQueryDSLImpl implements ProgramQueryDSL {
    private final JPAQueryFactory queryDSL;
    private final QBean<ProgramDTO> programDTOQuery = Projections.fields(ProgramDTO.class,
            program.id, program.house.member.id, program.createdDate,
            program.house.houseAddress, program.programName, program.programContent, program.verified
    );

    @Override
    public Page<ProgramDTO> findAllWithLimit(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null ? program.programName.contains(keyword) : null;

        final List<ProgramDTO> programDTOs = queryDSL
                .select(programDTOQuery).from(program).where(hasKeyword).fetch();
        Long count = queryDSL.select(program.count()).from(program).fetchOne();
        return new PageImpl<>(programDTOs, pageable, count == null ? 0 : count);
    }
}
