package com.howthere.app.repository.diary.reply;

import com.howthere.app.domain.diary.DiaryReplyDTO;
import com.howthere.app.domain.diary.QDiaryReplyDTO;
import com.howthere.app.entity.diary.DiaryReply;
import com.howthere.app.entity.diary.QDiaryReply;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.howthere.app.entity.diary.QDiaryReply.diaryReply;

@RequiredArgsConstructor
public class DiaryReplyQueryDSLImpl implements DiaryReplyQueryDSL {

    private final JPAQueryFactory query;


    @Override
    public Slice<DiaryReplyDTO> findAllWithSlice(Pageable pageable, Long id) {
        List<DiaryReplyDTO> diaryReplies = query.select(
                new QDiaryReplyDTO(
                        diaryReply.id,
                        diaryReply.replyContent,
                        diaryReply.member.id,
                        diaryReply.member.memberName,
                        diaryReply.diary.id
                )
            ).from(diaryReply)
            .where(diaryReply.diary.id.eq(id))
            .orderBy(diaryReply.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        boolean hasNext = false;

        if(diaryReplies.size() > pageable.getPageSize()){
            hasNext = true;
            diaryReplies.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(diaryReplies, pageable, hasNext);
    }

    @Override
    public void update(DiaryReply diaryReply) {
        query.update(QDiaryReply.diaryReply)
                .set(QDiaryReply.diaryReply.replyContent, diaryReply.getReplyContent())
                .where(QDiaryReply.diaryReply.id.eq(diaryReply.getId())).execute();
    }
}
