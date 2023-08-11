package com.howthere.app.service.diary.reply;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryReply;
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
}
