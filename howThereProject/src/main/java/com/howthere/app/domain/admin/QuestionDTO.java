package com.howthere.app.domain.admin;

import com.howthere.app.type.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@NoArgsConstructor
public class QuestionDTO {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private Long id;
    private String memberName;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    private LocalDateTime createdDate;
    private Long answerId;
    private String answerContent;

    public static DateTimeFormatter getFormat() {
        return format;
    }
}
