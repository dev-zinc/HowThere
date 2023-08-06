package com.howthere.repository;

import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class AnnouncementRepositoryTest {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test
    public void saveTest(){
        Announcement announcement = Announcement.builder()
                .id(1L)
                .announcementContent("test content")
                .announcementTitle("test title")
                .build();
        announcementRepository.save(announcement);
    }
}
