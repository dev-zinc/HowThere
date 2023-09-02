package com.howthere.app.service.diary.reply;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.entity.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryReplyService {
//    댓글 목록 더보기
    public Slice<DiaryReplyDTO> getListBySlice(Pageable pageable, Long id);

//    댓글 작성
    public void write(DiaryReplyDTO diaryReplyDTO);

//    댓글 수정
    public void update(DiaryReplyDTO diaryReplyDTO);

//    댓글 삭제
    public void remove(Long id);

//    댓글 전체 삭제
    public void removeByDiaryId(Long diaryId);


//    댓글 수
    public Long getReplyCount(Long id);


    default DiaryReply toEntity(DiaryReplyDTO diaryReplyDTO){
        return DiaryReply.builder()
                .id(diaryReplyDTO.getId())
                .replyContent(diaryReplyDTO.getReplyContent())
                .build();
    }

    default DiaryReply toEntity(DiaryReplyDTO diaryReplyDTO, Member member, Diary diary){
        return DiaryReply.builder()
                .id(diaryReplyDTO.getId())
                .replyContent(diaryReplyDTO.getReplyContent())
                .member(member)
                .diary(diary)
                .build();
    }
}
