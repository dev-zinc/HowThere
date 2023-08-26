package com.howthere.app.repository.program;

import static com.howthere.app.entity.file.QHouseFile.houseFile;
import static com.howthere.app.entity.program.QProgram.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.type.Verified;
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
public class ProgramQueryDSLImpl implements ProgramQueryDSL {

    private final JPAQueryFactory queryDSL;
    private final QBean<ProgramDTO> programDTOQuery = Projections.fields(ProgramDTO.class,
        program.id,
        ExpressionUtils.as(program.house.member.id, "memberId"),
        program.createdDate,
        ExpressionUtils.as(program.house.houseAddress.address, "programAddress"),
        ExpressionUtils.as(program.house.houseAddress.addressDetail, "programAddressDetail"),
        program.programName,
        program.programContent,
        program.programStartDate,
        program.programEndDate,
        program.programPrice,
        program.verified,
        ExpressionUtils.as(houseFile.filePath.append("/").append(houseFile.fileUuid), "thumbnail")
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

    @Override
    public Page<ProgramDTO> findAllWithThumbnail(Pageable pageable) {
        final List<ProgramDTO> programDTOs = queryDSL
            .select(programDTOQuery)
            .from(program)
            .innerJoin(houseFile)
            .on(houseFile.thumb.isTrue().and(houseFile.house.id.eq(program.house.id)))
            .where(program.verified.eq(Verified.Y))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(program.id.asc())
            .fetch();
        Long count = queryDSL.select(program.count()).from(program).fetchOne();
        return new PageImpl<>(programDTOs, pageable, count == null ? 0 : count);
    }
}
