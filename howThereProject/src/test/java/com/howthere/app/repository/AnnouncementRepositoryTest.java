package com.howthere.app.repository;

import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
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

import java.awt.print.Pageable;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
@RequiredArgsConstructor
public class AnnouncementRepositoryTest {

    @Autowired
    private AnnouncementRepository announcementRepository;

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
        PageRequest createDate = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Announcement> all = announcementRepository.findAll(createDate);
        all.getContent().forEach(el -> {
            log.info(el.toString());
        });
    }

    @Test
    public void countTest(){
        log.info("================================================================");
        log.info("count all {}", announcementRepository.count());
        log.info("================================================================");
    }
}
