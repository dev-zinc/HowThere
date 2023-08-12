package com.howthere.app.repository.admin;

import com.howthere.app.domain.QuestionDTO;
import com.howthere.app.domain.QuestionDetailDTO;
import com.howthere.app.entity.admin.QAnswer;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.howthere.app.entity.admin.QAnswer.answer;
import static com.howthere.app.entity.admin.QQuestion.question;

@RequiredArgsConstructor
public class QuestionQueryDSLImpl implements QuestionQueryDSL {

    private final JPAQueryFactory query;

    @Override
    public Page<QuestionDTO> findMyQuestions(Long memberId, String searchText, Pageable pageable) {
        BooleanExpression isMemberId = memberId == null ? null : question.member.id.eq(memberId);
        BooleanExpression searchTextContains = StringUtils.isNullOrEmpty(searchText)
                ? null : question.oneToOneQuestionContent.contains(searchText);

        List<QuestionDTO> dtoList = query
                .select(Projections.fields(QuestionDTO.class
                    , question.id
                    , question.oneToOneQuestionContent
                    , question.oneToOneQuestionType
                    ,question.createdDate
                ))
                .from(question)
                .where(isMemberId, searchTextContains)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long size = query.select(question.count())
                .from(question)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchOne();

        return new PageImpl<>(dtoList, pageable, size);
    }

    @Override
    public QuestionDetailDTO findQnAById(Long id) {
        return  query.select(Projections.fields(QuestionDetailDTO.class
                    , question.id
                    , question.createdDate
                    , question.oneToOneQuestionContent
                    , question.oneToOneQuestionType
                    , answer.answerContent
                    , answer.id.as("answerId")))
                .from(question)
                .leftJoin(answer)
                .on(answer.question.id.eq(question.id))
                .where(question.id.eq(id))
                .fetchOne();
    }
}
