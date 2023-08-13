package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.querydsl.core.types.ExpressionUtils;
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
            program.id, ExpressionUtils.as(program.house.member.id, "memberId"), program.createdDate,
            ExpressionUtils.as(program.house.houseAddress.address, "programAddress"), program.programName,
            program.programContent, program.verified
    );

    @Override
    public Page<ProgramDTO> findAllWithLimit(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null
                ? program.programName.contains(keyword).or(program.programContent.contains(keyword))
                : null;

        final List<ProgramDTO> programDTOs = queryDSL
                .select(programDTOQuery)
                .from(program)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(hasKeyword)
                .orderBy(program.id.asc())
                .fetch();
        Long count = queryDSL.select(program.count()).from(program).where(hasKeyword).fetchOne();
        return new PageImpl<>(programDTOs, pageable, count == null ? 0 : count);
    }
}
