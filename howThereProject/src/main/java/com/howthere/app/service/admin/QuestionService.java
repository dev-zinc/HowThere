package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.admin.QuestionDetailDTO;
import com.howthere.app.entity.admin.Answer;
import com.howthere.app.entity.admin.Question;

public interface QuestionService {

    void answerSave(QuestionDetailDTO dto);

    default Question toEntity(QuestionDTO dto){
        return Question.builder()
                .id(dto.getId())
                .oneToOneQuestionContent(dto.getOneToOneQuestionContent())
                .oneToOneQuestionType(dto.getOneToOneQuestionType())
                .build();
    }

    default Question toEntity(QuestionDetailDTO dto){
        return Question.builder()
                .id(dto.getId())
                .oneToOneQuestionType(dto.getOneToOneQuestionType())
                .oneToOneQuestionContent(dto.getOneToOneQuestionContent())
                .build();
    }

    default Answer toAnswer(QuestionDetailDTO dto){
        return Answer.builder()
                .id(dto.getAnswerId())
                .answerContent(dto.getAnswerContent())
                .question(toEntity(dto))
                .build();
    }
}
