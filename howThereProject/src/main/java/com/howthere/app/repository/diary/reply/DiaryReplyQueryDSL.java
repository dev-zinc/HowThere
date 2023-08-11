package com.howthere.app.repository.diary.reply;

import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface DiaryReplyQueryDSL {
//    댓글 목록 조회 (더보기)
    public Slice<DiaryReplyDTO> findAllWithSlice(Pageable pageable, Long id);

//    댓글 수정
    public void update(DiaryReply diaryReply);
}
