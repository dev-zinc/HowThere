package com.howthere.app.repository.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.admin.QAnnouncement.announcement;


@RequiredArgsConstructor
public class AnnouncementQueryDSLImpl implements AnnouncementQueryDSL {
    private final JPAQueryFactory query;
    private static final QBean<AnnouncementDTO> announcementDTO =
            Projections.fields(
                AnnouncementDTO.class,
                announcement.id,
                announcement.announcementTitle,
                announcement.announcementContent,
                announcement.createdDate,
                announcement.updatedDate
            );

    @Override
    public Page<AnnouncementDTO> findAllQueryDSL(Pageable pageable, String keyword) {
        BooleanExpression hasKeyword = keyword != null ? announcement.announcementTitle.contains(keyword)
                .and(announcement.announcementContent.contains(keyword)) : null;

        List<AnnouncementDTO> announcementList = query
                .select(announcementDTO)
                .from(announcement)
                .where(hasKeyword)
                .orderBy(announcement.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long count = query.select(announcement.count()).from(announcement).fetchOne();
        return new PageImpl<>(announcementList, pageable, count != null ? count : 0);
    }
}
