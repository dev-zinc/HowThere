package com.howthere.app.service.diary.like;

import com.howthere.app.domain.diary.DiaryLikeDTO;

public interface DiaryLikeService {
//    좋아요 추가
    public void like(DiaryLikeDTO diaryLikeDTO);

//    memberId와 diaryId로 id 조회
    public Long getId(Long memberId, Long diaryId);

//    좋아요 삭제
    public void removeLike(Long id);

//    좋아요 수
    public Long getLikeCount(Long id);
}
