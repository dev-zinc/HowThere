package com.howthere.app.domain;


import com.howthere.app.type.QuestionType;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QuestionDetailDTO {
    private Long id;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    private Long answerId;
    private String answerContent;
}
