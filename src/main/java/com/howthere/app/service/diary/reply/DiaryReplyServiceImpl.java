package com.howthere.app.service.diary.reply;

import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.diary.reply.DiaryReplyRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DiaryReplyServiceImpl implements DiaryReplyService {
    private final DiaryReplyRepository diaryReplyRepository;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    @Override
    public Slice<DiaryReplyDTO> getListBySlice(Pageable pageable, Long id) {
        return diaryReplyRepository.findAllWithSlice(pageable, id);
    }

    @Override
    public void write(DiaryReplyDTO diaryReplyDTO) {
        log.info(diaryReplyDTO.toString());
        Member member = memberRepository.findById(diaryReplyDTO.getMemberId()).orElseThrow();
        Diary diary = diaryRepository.findById(diaryReplyDTO.getDiaryId()).orElseThrow();
        diaryReplyRepository.save(toEntity(diaryReplyDTO, member, diary));
    }

    @Override
    public void update(DiaryReplyDTO diaryReplyDTO) {
        diaryReplyRepository.update(toEntity(diaryReplyDTO));
    }

    @Override
    public void remove(Long id) {
        diaryReplyRepository.deleteById(id);
    }

    @Override
    public void removeByDiaryId(Long diaryId) {
        diaryReplyRepository.deleteByDiaryId(diaryId);
    }

    @Override
    public Long getReplyCount(Long id) {
        return diaryReplyRepository.countReply(id);
    }

//    public DiaryReply toEntity(DiaryReplyDTO diaryReplyDTO){
//        return DiaryReply.builder()
//                .id(diaryReplyDTO.getId())
//                .replyContent(diaryReplyDTO.getReplyContent())
//                .member(memberRepository.findById(diaryReplyDTO.getMemberId()).get())
//                .diary(diaryRepository.findById(diaryReplyDTO.getDiaryId()).get())
//                .build();
//    }
}
