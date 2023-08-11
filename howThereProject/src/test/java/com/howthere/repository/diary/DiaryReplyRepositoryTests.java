package com.howthere.repository.diary;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.diary.reply.DiaryReplyRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.service.diary.DiaryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(value = false)
public class DiaryReplyRepositoryTests {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private DiaryReplyRepository diaryReplyRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        final Member member = memberRepository.findById(2L).orElseThrow(RuntimeException::new);
        final Diary diary = diaryRepository.findById(6L).orElseThrow(RuntimeException::new);

        for(int i=0; i<100; i++){
            DiaryReply diaryReply = DiaryReply.builder()
                    .replyContent("테스트 댓글" + ( i + 1 ))
                    .member(member)
                    .diary(diary)
                    .build();
            diaryReplyRepository.save(diaryReply);
        }
    }

    @Test
    public void findAllWithSliceTest(){
        Slice<DiaryReplyDTO> diaryReplies = diaryReplyRepository.findAllWithSlice(PageRequest.of(0, 5), 6L);
        log.info(diaryReplies.getContent().toString());
    }

    @Test
    public void updateTest(){
        Optional<DiaryReply> foundDiaryReply = diaryReplyRepository.findById(258L);
        foundDiaryReply.ifPresent(diaryReply -> {
//            diaryReply.setReplyContent("수정된 댓글");
            diaryReplyRepository.update(diaryReply);
        });
    }

    @Test
    public void deleteTest(){
        diaryReplyRepository.deleteById(258L);
    }
}
