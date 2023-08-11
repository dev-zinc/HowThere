package com.howthere.app.repository.diary.like;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DiaryLikeQueryDSLImpl implements DiaryLikeQueryDSL {
    private final JPAQueryFactory query;
}
