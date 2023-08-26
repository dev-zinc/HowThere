package com.howthere.app.repository.member;

import com.howthere.app.domain.member.MemberInfoDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.member.QMember.member;

@RequiredArgsConstructor
public class MemberQueryDSLImpl implements MemberQueryDSL {
    private final JPAQueryFactory query;

    private static final QBean<MemberInfoDTO> memberInfoDTO = Projections.fields(MemberInfoDTO.class,
            member.id,
            member.memberEmail,
            member.memberName,
            member.memberBirthDate,
            member.createdDate,
            member.deleted.as("activated")
            );

    @Override
    public Page<MemberInfoDTO> getMembers(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null
                ? member.memberName.contains(keyword) : null;

        List<MemberInfoDTO> memberDTOs = query.select(memberInfoDTO)
                .from(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(hasKeyword)
                .fetch();
        Long count = query.select(member.count()).from(member).where(hasKeyword).fetchOne();
        return new PageImpl<>(memberDTOs, pageable, count != null ? count : 0);
    }
}
