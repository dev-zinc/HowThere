package com.howthere.app.service.diary.reply;

import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.diary.reply.DiaryReplyRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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
        diaryReplyRepository.save(toEntity(diaryReplyDTO));
    }

    @Override
    public void update(DiaryReplyDTO diaryReplyDTO) {
        diaryReplyRepository.update(toEntity(diaryReplyDTO));
    }

    @Override
    public void remove(Long id) {
        diaryReplyRepository.deleteById(id);
    }

    public DiaryReply toEntity(DiaryReplyDTO diaryReplyDTO){
        return DiaryReply.builder()
                .id(diaryReplyDTO.getId())
                .replyContent(diaryReplyDTO.getReplyContent())
                .member(memberRepository.findById(diaryReplyDTO.getMemberId()).get())
                .diary(diaryRepository.findById(diaryReplyDTO.getDiaryId()).get())
                .build();
    }
}
