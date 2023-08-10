package com.howthere.repository;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.file.DiaryFile;

import java.util.UUID;

import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.file.DiaryFileRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(value = false)
public class DiaryFileRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private DiaryFileRepository diaryFileRepository;

    @Test
    public void saveTest() {
        final Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        final Diary diary = Diary.builder()
                .diaryTitle("title")
                .diaryContent("content")
                .member(member)
                .build();

        Diary savedDiary = diaryRepository.save(diary);

        final DiaryFile diaryFile = DiaryFile.builder()
                .fileName("name")
                .filePath("path")
                .fileSize(10_000L)
                .fileUuid(UUID.randomUUID().toString())
                .diary(savedDiary)
                .build();

        diaryFileRepository.save(diaryFile);
    }

    @Test
    public void findTest() {
        diaryFileRepository.findById(3L).ifPresent(diaryFile -> log.info(diaryFile.toString()));
    }
}
