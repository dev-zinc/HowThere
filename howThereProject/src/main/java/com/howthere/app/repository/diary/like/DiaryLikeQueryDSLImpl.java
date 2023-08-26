package com.howthere.app.repository.diary.like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.howthere.app.entity.diary.QDiaryLike.diaryLike;

@RequiredArgsConstructor
public class DiaryLikeQueryDSLImpl implements DiaryLikeQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Long findId(Long memberId, Long diaryId) {
        return query.select(diaryLike.id).from(diaryLike).where(diaryLike.member.id.eq(memberId).and(diaryLike.diary.id.eq(diaryId))).fetchOne();
    }

    @Override
    public Long countLike(Long id) {
        return query.select(diaryLike.count()).from(diaryLike).where(diaryLike.diary.id.eq(id)).fetchOne();
    }

    @Override
    public void deleteByDiaryId(Long diaryId) {
        query.delete(diaryLike).where(diaryLike.diary.id.eq(diaryId)).execute();
    }
}
