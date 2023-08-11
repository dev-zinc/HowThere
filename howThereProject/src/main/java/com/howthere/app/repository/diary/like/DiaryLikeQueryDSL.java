package com.howthere.app.repository.diary.like;

public interface DiaryLikeQueryDSL {
//    member와 diary로 좋아요 id 찾아내기
    public Long findId(Long memberId, Long diaryId);

//    좋아요 수
    public Long countLike(Long id);
}
