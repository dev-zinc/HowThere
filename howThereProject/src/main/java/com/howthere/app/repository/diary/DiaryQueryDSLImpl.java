package com.howthere.app.repository.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.QDiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.QDiary;
import com.howthere.app.entity.diary.QDiaryReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.howthere.app.entity.diary.QDiary.diary;

@RequiredArgsConstructor
public class DiaryQueryDSLImpl implements DiaryQueryDSL {

    private final JPAQueryFactory query;

    @PersistenceContext
    private final EntityManager entityManager;

//    @Override
//    public List<Diary> findAll() {
//        List<Diary> diarys = query.select(diary).from(diary).fetch();
//
//        return diarys;
//    }

//    @Override
//    public Page<Diary> findAllWithPaging(Pageable pageable) {
//        final List<Diary> diarys = query.select(diary).from(diary)
//                .orderBy(diary.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        final Long count = query.select(diary.count()).from(diary).fetchOne();
//
//        return new PageImpl<>(diarys, pageable, count);
//    }

    @Override
    public Slice<DiaryDTO> findAllWithSlice(Pageable pageable, String keyword, String order) {
        BooleanExpression diaryTitleContains = keyword == null || keyword == "" ? null : diary.diaryTitle.contains(keyword);
        BooleanExpression diaryContentContains = keyword == null || keyword == "" ? null : diary.diaryContent.contains(keyword);
        OrderSpecifier<Long> orderSort = order.equals("recent") || order.equals("") ? diary.id.desc() : diary.diaryViewCount.desc();

        List<DiaryDTO> diarys = query.select(
                new QDiaryDTO(
                        diary.id,
                        diary.member.id,
                        diary.member.memberName,
                        diary.house.id,
                        diary.diaryTitle,
                        diary.diaryContent,
                        diary.diaryViewCount)
                ).from(diary)
                .where(diaryTitleContains.or(diaryContentContains))
                .orderBy(orderSort)
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
    public Long findId() {
        return query.select(diary.id).from(diary).orderBy(diary.id.desc()).limit(1).fetchOne();
    }

    @Override
    public void update(Diary diary) {
        query.update(QDiary.diary)
                .set(QDiary.diary.diaryTitle, diary.getDiaryTitle())
                .set(QDiary.diary.diaryContent, diary.getDiaryContent())
                .where(QDiary.diary.id.eq(diary.getId())).execute();
    }

    @Override
    public void updateViewCount(Diary diary) {
        query.update(QDiary.diary)
                .set(QDiary.diary.diaryViewCount, QDiary.diary.diaryViewCount.add(1))
                .where(QDiary.diary.id.eq(diary.getId()))
                .execute();

        entityManager.clear();
    }
}
