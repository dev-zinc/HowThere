package com.howthere.app.repository.house;

import static com.howthere.app.entity.house.QHouse.house;

import com.howthere.app.domain.house.HouseDTO;
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
public class HouseQueryDSLImpl implements HouseQueryDSL {

    private final JPAQueryFactory query;

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
}
