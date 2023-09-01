package com.howthere.app.repository.program;

import static com.howthere.app.entity.program.QProgramPayment.programPayment;

import com.howthere.app.domain.program.ProgramPaymentDTO;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProgramPaymentQueryDSLImpl implements ProgramPaymentQueryDSL {

    private final JPAQueryFactory query;

    private final QBean<ProgramPaymentDTO> programPaymentDTO = Projections.fields(
        ProgramPaymentDTO.class,
        programPayment.paymentPrice,
        ExpressionUtils.as(programPayment.program.programName, "programName"),
        ExpressionUtils.as(programPayment.program.programStartDate, "programStartDate"),
        ExpressionUtils.as(programPayment.program.programEndDate, "programEndDate"),
        ExpressionUtils.as(programPayment.program.id, "programId")
    );

    @Override
    public Page<ProgramPaymentDTO> getPaymentListByMemberId(Pageable pageable, Long memberId) {
        final List<ProgramPaymentDTO> paymentDTOList = query.select(programPaymentDTO)
            .from(programPayment)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .where(programPayment.member.id.eq(memberId))
            .fetch();

        final Long count = query.select(programPayment.count())
            .from(programPayment)
            .fetchOne();
        return new PageImpl<>(paymentDTOList, pageable, count != null ? count : 0);
    }
}
