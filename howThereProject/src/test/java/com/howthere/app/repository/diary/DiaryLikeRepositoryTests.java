package com.howthere.app.repository.diary;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.diary.like.DiaryLikeRepository;
import com.howthere.app.repository.diary.reply.DiaryReplyRepository;
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
public class DiaryLikeRepositoryTests {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private DiaryLikeRepository diaryLikeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        final Member member = memberRepository.findById(2L).orElseThrow(RuntimeException::new);
        final Diary diary = diaryRepository.findById(6L).orElseThrow(RuntimeException::new);

        DiaryLike diaryLike = DiaryLike.builder().member(member).diary(diary).build();
        diaryLikeRepository.save(diaryLike);

    }

    @Test
    public void deleteTest(){
        final Member member = memberRepository.findById(2L).orElseThrow(RuntimeException::new);
        final Diary diary = diaryRepository.findById(6L).orElseThrow(RuntimeException::new);

        DiaryLike diaryLike = DiaryLike.builder().id(291L).member(member).diary(diary).build();
        diaryLikeRepository.delete(diaryLike);
    }
}
