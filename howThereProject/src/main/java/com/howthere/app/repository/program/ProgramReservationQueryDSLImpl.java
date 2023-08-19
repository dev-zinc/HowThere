package com.howthere.app.repository.program;

import com.howthere.app.domain.program.ProgramReservationDTO;
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

import static com.howthere.app.entity.program.QProgramReservation.programReservation;

@RequiredArgsConstructor
public class ProgramReservationQueryDSLImpl implements ProgramReservationQueryDSL {
    private final JPAQueryFactory query;
    private final QBean<ProgramReservationDTO> programReservationDTO = Projections.fields(ProgramReservationDTO.class,
            programReservation.id,
            ExpressionUtils.as(programReservation.program.programName, "programName"),
            ExpressionUtils.as(programReservation.program.house.houseTitle, "houseTitle"),
            ExpressionUtils.as(programReservation.createdDate, "reservedDate"),
            ExpressionUtils.as(programReservation.member.id, "memberId"),
            ExpressionUtils.as(programReservation.host.id, "hostId"),
            programReservation.verified);

    @Override
    public Page<ProgramReservationDTO> findAllWithKeyword(Pageable pageable, String keyword) {
        final BooleanExpression hasKeyword = keyword != null
                ? programReservation.program.programContent.contains(keyword)
                        .or(programReservation.program.programName.contains(keyword))
                        .or(programReservation.program.house.houseTitle.contains(keyword))
                        .or(programReservation.program.house.houseContent.contains(keyword))
                : null;
        final List<ProgramReservationDTO> programReservationDTOs = query
                .select(programReservationDTO)
                .from(programReservation)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(hasKeyword)
                .fetch();
        final Long count = query.select(programReservation.count()).from(programReservation).fetchOne();

        return new PageImpl<>(programReservationDTOs, pageable, count != null ? count : 0);
    }
}
