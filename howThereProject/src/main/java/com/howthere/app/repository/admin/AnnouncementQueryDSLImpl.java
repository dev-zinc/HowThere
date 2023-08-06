package com.howthere.app.repository.admin;

import com.howthere.app.domain.AnnouncementDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.admin.QAnnouncement;
import com.querydsl.core.types.Projections;
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

    @Override
    public Page<AnnouncementDTO> findAllQueryDSL(Pageable pageable) {
        List<AnnouncementDTO> announcementList = query
                .select(Projections.fields(
                        AnnouncementDTO.class,
                        announcement.id,
                        announcement.announcementTitle,
                        announcement.announcementContent
                )).from(announcement).fetch();
        return new PageImpl<>(announcementList, pageable, announcementList.size());
    }
}
