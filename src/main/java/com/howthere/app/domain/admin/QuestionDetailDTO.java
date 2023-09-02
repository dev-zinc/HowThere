package com.howthere.app.domain.admin;


import com.howthere.app.type.QuestionType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class QuestionDetailDTO {
    private Long id;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    private Long answerId;
    private String answerContent;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;

    @Builder
    public QuestionDetailDTO(Long id, String oneToOneQuestionContent, QuestionType oneToOneQuestionType, Long answerId, String answerContent, LocalDateTime createdDate) {
        this.id = id;
        this.oneToOneQuestionContent = oneToOneQuestionContent;
        this.oneToOneQuestionType = oneToOneQuestionType;
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.createdDate = createdDate;
    }
}
