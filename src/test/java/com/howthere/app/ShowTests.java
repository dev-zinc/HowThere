package com.howthere.app;

import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.admin.AnnouncementRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ShowTests {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AnnouncementRepository announcementRepository;
    @Test
    public void saveTest() {
        Member admin = memberRepository.findById(1L).orElseThrow();
        IntStream.rangeClosed(1, 12)
                .forEach(i -> {
                    Announcement announcement = Announcement.builder()
                            .announcementTitle("[공지사항] " + i + "월 이벤트 안내")
                            .announcementContent(
                                    i+"월 프로그램 공지: 이번 달에는 다양한 이벤트와 활동이 기다리고 있습니다! \n"
                                    + "문화 축제, 야외 콘서트, 가족 행사 등 다양한 활동으로 여름을 더욱 즐겁게 보내보세요. \n"
                                    + "일정 및 상세 내용은 웹사이트를 확인해주세요. 잊지 말고 참석 일정을 기록해두세요! \uD83C\uDF1E\uD83C\uDF89")
                            .admin(admin)
                            .build();
                    announcementRepository.save(announcement);
                });
    }
    
}
