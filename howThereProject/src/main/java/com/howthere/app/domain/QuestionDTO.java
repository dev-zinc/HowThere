package com.howthere.app.domain;

import com.howthere.app.type.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    private LocalDateTime createdDate;
}
