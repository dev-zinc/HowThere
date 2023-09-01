package com.howthere.app.repository.program;

import static com.howthere.app.entity.file.QHouseFile.houseFile;
import static com.howthere.app.entity.house.QHouse.house;
import static com.howthere.app.entity.program.QProgram.program;

import com.howthere.app.domain.Search;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.domain.program.ProgramListDTO;
import com.howthere.app.domain.program.ProgramMainDTO;
import com.howthere.app.type.Verified;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

@RequiredArgsConstructor
public class ProgramQueryDSLImpl implements ProgramQueryDSL {

    private final JPAQueryFactory queryDSL;

    private final QBean<ProgramMainDTO> programMainDTO = Projections.fields(ProgramMainDTO.class,
            program.id,
            program.house.houseAddress.address.as("programAddress"),
            program.programStartDate,
            program.programEndDate,
            program.programPrice,
            houseFile.fileName,
            houseFile.fileUuid,
            houseFile.filePath,
            houseFile.fileSize
    );

    private final QBean<ProgramListDTO> programListDTO = Projections.fields(ProgramListDTO.class,
            program.id,
            ExpressionUtils.as(program.house.member.id, "memberId"),
            program.createdDate,
            ExpressionUtils.as(program.house.houseAddress.address, "programAddress"),
            program.programName,
            program.programContent,
            program.verified
    );

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
    public Page<ProgramListDTO> findAllWithLimit(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null
            ? program.programName.contains(keyword).or(program.programContent.contains(keyword))
            : null;

        final List<ProgramListDTO> programDTOs = queryDSL
            .select(programListDTO)
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
    public Page<ProgramDTO> findAllWithThumbnail(Pageable pageable, Search search) {
        BooleanBuilder builder = new BooleanBuilder();
        
        if(search != null) {
            if(search.getPlace() != null) {
                builder.and(program.house.houseAddress.address.contains(search.getPlace()));
            }
            if(search.getCheckIn() != null) {
                builder.and(program.programStartDate.gt(search.getCheckIn()));
            }
            if(search.getCheckOut() != null) {
                builder.and(program.programEndDate.lt(search.getCheckOut()));
            }
            if(search.getGuestCnt() != null) {
                builder.and(program.house.houseMaxHeadCount.loe(search.getGuestCnt()));
            }
            if(search.getPetCnt() != null) {
                builder.and(program.house.houseMaxPetCount.loe(search.getPetCnt()));
            }
            builder.and(program.verified.eq(Verified.Y));
        }
        
        final List<ProgramDTO> programDTOs = queryDSL
            .select(programDTOQuery)
            .from(program)
            .innerJoin(house)
            .on(program.house.id.eq(house.id))
            .leftJoin(houseFile)
            .on(houseFile.thumb.isTrue().and(houseFile.house.id.eq(program.house.id)))
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(program.id.asc())
            .fetch();
        Long count = queryDSL.select(program.count()).from(program).fetchOne();
        return new PageImpl<>(programDTOs, pageable, count == null ? 0 : count);
    }

    @Override
    public List<ProgramMainDTO> findAll10(String region) {
        BooleanExpression hasRegion = region != null 
                ? program.house.houseAddress.address.contains(region).and(program.verified.eq(Verified.Y)) 
                : null;
        
        return queryDSL
                .select(programMainDTO)
                .from(program)
                .join(house).on(program.house.id.eq(house.id))
                .leftJoin(houseFile).on(program.house.id.eq(houseFile.house.id))
                .where(hasRegion)
                .limit(10)
                .fetch();
    }
}
