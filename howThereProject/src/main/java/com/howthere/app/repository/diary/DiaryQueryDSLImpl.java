package com.howthere.app.repository.diary;

import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.QDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;

import static com.howthere.app.entity.diary.QDiary.diary;

@RequiredArgsConstructor
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
        final List<Diary> diarys = query.select(diary).from(diary)
                .orderBy(diary.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        final Long count = query.select(diary.count()).from(diary).fetchOne();

        return new PageImpl<>(diarys, pageable, count);
    }

    @Override
    public Slice<Diary> findAllWithSlice(Pageable pageable) {
        List<Diary> diarys = query.selectFrom(diary)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;

        if(diarys.size() > pageable.getPageSize()){
            hasNext = true;
            diarys.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(diarys, pageable, hasNext);
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
