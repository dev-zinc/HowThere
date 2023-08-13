package com.howthere.app.repository;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
import com.howthere.app.service.admin.AnnouncementService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.howthere.app.entity.admin.QAnnouncement.announcement;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
@RequiredArgsConstructor
public class AnnouncementRepositoryTest {

    @Autowired
    private JPAQueryFactory query;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementService announcementService;

    @Test
    public void saveTest(){
        log.info("=========================== test start");
        for(int i = 1; i <= 100; i++){
            Announcement announcement = Announcement.builder()
                    .announcementContent("test content" + i)
                    .announcementTitle("test title" + i)
                    .build();
            announcementRepository.save(announcement);
        }
        log.info("============================= test end");
    }

    @Test
    public void finaAllByPageableTest(){
        PageRequest createDate = PageRequest.of(3, 10, Sort.by(Sort.Direction.DESC, "id"));
//        Page<Announcement> all = announcementRepository.findAll(createDate);
//        all.getContent().forEach(el -> {
//            log.info(el.toString());
//        });
        Page<AnnouncementDTO> announcementList = announcementService.getAnnouncementList(createDate);

        log.info(announcementList.toString());
    }

    @Test
    public void countTest(){
        log.info("================================================================");
        log.info("count all {}", announcementRepository.count());
        log.info("================================================================");
    }

    @Test
    public void test(){
        PageRequest request = PageRequest.of(0, 10);
//        List<Announcement> announcementList = query
//                .selectFrom(announcement)
//                .orderBy(announcement.createdDate.desc())
//                .offset(request.getOffset())
//                .limit(request.getPageSize())
////                .orderBy(announcement.createdDate.desc())
//                .fetch();

        List<AnnouncementDTO> announcementList = query
                .select(Projections.fields(
                        AnnouncementDTO.class,
                        announcement.id,
                        announcement.announcementTitle,
                        announcement.announcementContent
                )).from(announcement)
                .orderBy(announcement.createdDate.desc())
                .offset(request.getOffset())
                .limit(request.getPageSize())
//                .orderBy(announcement.createdDate.desc())
                .fetch();

        announcementList.stream()
                .map(AnnouncementDTO::toString)
                .forEach(log::info);
    }
}
