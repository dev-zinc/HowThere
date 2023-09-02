package com.howthere.app.repository.program;

import static com.howthere.app.entity.program.QProgramReservation.programReservation;

import com.howthere.app.domain.program.ProgramReservationDTO;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProgramReservationQueryDSLImpl implements ProgramReservationQueryDSL {

    private final JPAQueryFactory query;
    private final QBean<ProgramReservationDTO> programReservationDTO = Projections.fields(
        ProgramReservationDTO.class,
        programReservation.id,
        ExpressionUtils.as(programReservation.program.programName, "programName"),
        ExpressionUtils.as(programReservation.program.house.houseTitle, "houseTitle"),
        ExpressionUtils.as(programReservation.createdDate, "reservedDate"),
        ExpressionUtils.as(programReservation.member.id, "memberId"),
        ExpressionUtils.as(programReservation.host.id, "hostId"),
        ExpressionUtils.as(programReservation.program.house.id, "houseId"),
        ExpressionUtils.as(programReservation.program.programContent, "programContent"),
        ExpressionUtils.as(programReservation.program.programStartDate, "programStartDate"),
        ExpressionUtils.as(programReservation.program.programEndDate, "programEndDate"),
        ExpressionUtils.as(programReservation.program.programPrice, "programPrice"),
        ExpressionUtils.as(programReservation.program.id, "programId"),
        programReservation.verified,
        programReservation.confirm
    );

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
        final Long count = query.select(programReservation.count())
            .from(programReservation)
            .fetchOne();

        return new PageImpl<>(programReservationDTOs, pageable, count != null ? count : 0);
    }

    @Override
    public Page<ProgramReservationDTO> getReservationByMemberId(Pageable pageable, Long id) {
        final List<ProgramReservationDTO> programReservationDTOs = query
            .select(programReservationDTO)
            .from(programReservation)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .where(programReservation.member.id.eq(id))
            .orderBy(programReservation.id.asc())
            .fetch();
        final Long count = query.select(programReservation.count())
            .from(programReservation)
            .where(programReservation.member.id.eq(id))
            .fetchOne();
        return new PageImpl<>(programReservationDTOs, pageable, count != null ? count : 0);
    }
}
