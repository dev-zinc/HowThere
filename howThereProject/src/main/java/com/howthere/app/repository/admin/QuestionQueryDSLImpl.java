package com.howthere.app.repository.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.admin.QuestionDetailDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.howthere.app.entity.admin.QAnswer.answer;
import static com.howthere.app.entity.admin.QQuestion.question;

@RequiredArgsConstructor
public class QuestionQueryDSLImpl implements QuestionQueryDSL {

    private final JPAQueryFactory query;

    private final QBean<QuestionDTO> questionDTO = Projections.fields(QuestionDTO.class,
            question.id,
            question.member.memberName.as("memberName"),
            question.oneToOneQuestionContent,
            question.oneToOneQuestionType,
            question.createdDate,
            answer.answerContent
    );

    @Override
    public Page<QuestionDTO> findMyQuestions(Long memberId, Pageable pageable) {
        BooleanExpression isMemberId = memberId == null ? null : question.member.id.eq(memberId);

        List<QuestionDTO> dtoList = query
                .select(Projections.fields(QuestionDTO.class
                    , question.id
                    , question.oneToOneQuestionContent
                    , question.oneToOneQuestionType
                    ,question.createdDate
                ))
                .from(question)
                .where(isMemberId)
                .orderBy(question.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long size = query.select(question.count())
                .from(question)
                .fetchOne();

        return new PageImpl<>(dtoList, pageable, size);
    }

    @Override
    public Page<QuestionDTO> findAllWithKeyword(Pageable pageable, String keyword) {
        final BooleanExpression hasKeyword = keyword != null
                ? question.oneToOneQuestionContent.contains(keyword)
                : null;
        final List<QuestionDTO> questionDTOs = query
                .select(questionDTO)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .from(question)
                .leftJoin(answer)
                .on(answer.question.id.eq(question.id).and(hasKeyword))
                .fetch();

        final Long count = query.select(question.count()).from(question).fetchOne();
        return new PageImpl<>(questionDTOs, pageable, count != null ? count : 0);
    }

    @Override
    public Optional<QuestionDTO> findDTOById(Long id) {
        return Optional.ofNullable(query
                .select(questionDTO)
                .from(question)
                .leftJoin(answer)
                .on(answer.question.id.eq(question.id).and(question.id.eq(id)))
                .fetchOne());
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

    @Override
    public Page<QuestionDetailDTO> findQnAByMemberIdAndSearchText(String searchText, Pageable pageable) {
        BooleanExpression searchTextContains = StringUtils.isNullOrEmpty(searchText)
                ? null : question.oneToOneQuestionContent.contains(searchText);

        List<QuestionDetailDTO> dtoList = query
                .select(Projections.fields(QuestionDetailDTO.class
                        , question.id
                        , question.oneToOneQuestionContent
                        , question.oneToOneQuestionType
                        , question.createdDate
                        , answer.answerContent
                        , answer.id.as("answerId")
                ))
                .from(question)
                .leftJoin(answer)
                .on(answer.question.id.eq(question.id))
                .where(searchTextContains)
                .orderBy(question.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long size = query.select(question.count())
                .from(question)
                .leftJoin(answer)
                .on(answer.question.id.eq(question.id))
                .where(searchTextContains)
                .fetchOne();

        return new PageImpl<>(dtoList, pageable, size);
    }
}
