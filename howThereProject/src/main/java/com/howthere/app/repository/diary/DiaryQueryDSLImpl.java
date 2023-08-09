package com.howthere.app.repository.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.QDiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.diary.QDiary;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.howthere.app.entity.diary.QDiary.diary;

@RequiredArgsConstructor
public class DiaryQueryDSLImpl implements DiaryQueryDSL {

    private final JPAQueryFactory query;

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
    public Slice<DiaryDTO> findAllWithSlice(Pageable pageable) {
        List<DiaryDTO> diarys = query.select(
                new QDiaryDTO(
                        diary.id,
                        diary.member.id,
                        diary.member.memberName,
                        diary.house.id,
                        diary.diaryTitle,
                        diary.diaryContent,
                        diary.diaryViewCount
                        )
                ).from(diary)
                .join(diary.member)
                .join(diary.house)
//                .fetchJoin()
                .orderBy(diary.id.desc())
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
    public Optional<DiaryDTO> findById_QueryDSL(Long id) {
        DiaryDTO foundDiary = query.select(
                new QDiaryDTO(
                        diary.id,
                        diary.member.id,
                        diary.member.memberName,
                        diary.house.id,
                        diary.diaryTitle,
                        diary.diaryContent,
                        diary.diaryViewCount
                )
        ).from(diary)
                .join(diary.member)
                .join(diary.house)
                .where(diary.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(foundDiary);
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
