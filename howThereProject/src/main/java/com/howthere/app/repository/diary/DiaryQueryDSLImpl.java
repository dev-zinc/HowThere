package com.howthere.app.repository.diary;

import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.QDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.diary.QDiary.diary;


public class DiaryQueryDSLImpl implements DiaryQueryDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Diary> findAll() {
        List<Diary> diarys = query.select(diary).from(diary).fetch();

        return diarys;
    }

    @Override
    public Page<Diary> findAllWithPaging(Pageable pageable) {
        List<Diary> diarys = query.select(diary).from(diary)
                .orderBy(diary.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(diary.count()).from(diary).fetchOne();

        return new PageImpl<>(diarys, pageable, count);
    }

    @Override
    public void update(Diary diary) {
        query.update(QDiary.diary)
                .set(QDiary.diary.diaryTitle, diary.getDiaryTitle())
                .set(QDiary.diary.diaryContent, diary.getDiaryContent())
                .set(QDiary.diary.diaryViewCount, diary.getDiaryViewCount())
                .where(QDiary.diary.id.eq(diary.getId())).execute();
    }
}
