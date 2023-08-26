package com.howthere.app.repository.announcement;

import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class AnnouncementRepositoryTests {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test
    public void saveTest() {
        IntStream.range(0, 50).forEach(i -> {
            Announcement announcement = Announcement.builder()
                    .announcementTitle("title" + i)
                    .announcementContent("content" + i)
                    .build();
            announcementRepository.save(announcement);
        });
    }
}
