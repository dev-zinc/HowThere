package com.howthere.app.service.diary.like;

import com.howthere.app.domain.diary.DiaryLikeDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.member.Member;

public interface DiaryLikeService {
//    좋아요 추가
    public void like(DiaryLikeDTO diaryLikeDTO);

//    memberId와 diaryId로 id 조회
    public Long getId(Long memberId, Long diaryId);

//    좋아요 삭제
    public void removeLike(Long id);

//    좋아요 수
    public Long getLikeCount(Long id);


//    좋아요 전체 삭제
    public void removeByDiaryId(Long diaryId);

    default DiaryLike toEntity(DiaryLikeDTO diaryLikeDTO){
        return DiaryLike.builder()
                .id(diaryLikeDTO.getId())
                .build();
    }

    default DiaryLike toEntity(DiaryLikeDTO diaryLikeDTO, Member member, Diary diary){
        return DiaryLike.builder()
                .id(diaryLikeDTO.getId())
                .member(member)
                .diary(diary)
                .build();
    }
}
