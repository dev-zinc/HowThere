package com.howthere.app.repository.house;

import static com.howthere.app.entity.file.QHouseFile.houseFile;
import static com.howthere.app.entity.house.QHouse.house;

import com.howthere.app.domain.house.HouseDTO;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.QList;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class HouseQueryDSLImpl implements HouseQueryDSL {

    private static final QBean<HouseDTO> houseDTO = Projections.fields(HouseDTO.class,
        house.id,
        ExpressionUtils.as(house.houseAddress.address, "houseAddress"),
        ExpressionUtils.as(house.houseAddress.addressDetail, "houseAddressDetail"),
        ExpressionUtils.as(house.houseAddress.latitude, "lat"),
        ExpressionUtils.as(house.houseAddress.longitude, "lon"),
        house.houseTitle,
        house.houseContent,
        house.houseMaxHeadCount,
        house.houseMaxPetCount,
        house.createdDate,
        ExpressionUtils.as(house.member.id, "memberId")
    );
    private static final QBean<HouseDTO> editHouseDTO = Projections.fields(HouseDTO.class,
        house.id,
        ExpressionUtils.as(house.houseAddress.address, "houseAddress"),
        ExpressionUtils.as(house.houseAddress.addressDetail, "houseAddressDetail"),
        ExpressionUtils.as(house.houseAddress.latitude, "lat"),
        ExpressionUtils.as(house.houseAddress.longitude, "lon"),
        house.houseTitle,
        house.houseContent,
        house.houseMaxHeadCount,
        house.houseMaxPetCount,
        house.createdDate,
        ExpressionUtils.as(house.member.id, "memberId"),
        ExpressionUtils.as(houseFile.filePath.append("/").append(houseFile.fileUuid), "thumbnail")
    );
    private final JPAQueryFactory query;

    @Override
    public Page<HouseDTO> findWithLimitAndKeyword(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null
            ? house.houseTitle.contains(keyword).or(house.houseContent.contains(keyword))
            : null;

        final List<HouseDTO> houseDTOs =
            query.select(houseDTO)
                .from(house)
                .where(hasKeyword)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(house.count()).from(house).where(hasKeyword).fetchOne();

        return new PageImpl<>(houseDTOs, pageable, count != null ? count : 0);
    }

    @Override
    public Page<HouseDTO> findAllByIdWithPaging(Pageable pageable, Long memberId) {
        final List<HouseDTO> houseDTOs =
            query.select(editHouseDTO)
                .from(houseFile)
                .join(house)
                .on(house.id.eq(houseFile.house.id)
                    .and(house.member.id.eq(memberId)))
                .where(houseFile.thumb.isTrue())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(house.id.desc())
                .fetch();
        Long count = query.select(house.count())
            .from(house)
            .where(house.member.id.eq(memberId))
            .fetchOne();

        return new PageImpl<>(houseDTOs, pageable, count != null ? count : 0);
    }

    @Override
    public HouseDTO getHouse(Long id) {
        return query.select(editHouseDTO)
            .from(house)
            .where(house.id.eq(id))
            .join(houseFile)
            .on(houseFile.house.id.eq(id).and(houseFile.thumb.isTrue()))
            .fetchOne();
    }
}
