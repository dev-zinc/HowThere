package com.howthere.app.service.admin;

import com.howthere.app.domain.QuestionDTO;
import com.howthere.app.domain.QuestionDetailDTO;
import com.howthere.app.entity.admin.Question;

public interface QuestionService {
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
}
