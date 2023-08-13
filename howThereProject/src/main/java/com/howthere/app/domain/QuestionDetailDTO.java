package com.howthere.app.domain;


import com.howthere.app.type.QuestionType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class QuestionDetailDTO {
    private Long id;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    private Long answerId;
    private String answerContent;

    @Builder
    public QuestionDetailDTO(Long id, String oneToOneQuestionContent, QuestionType oneToOneQuestionType, Long answerId, String answerContent) {
        this.id = id;
        this.oneToOneQuestionContent = oneToOneQuestionContent;
        this.oneToOneQuestionType = oneToOneQuestionType;
        this.answerId = answerId;
        this.answerContent = answerContent;
    }
}
