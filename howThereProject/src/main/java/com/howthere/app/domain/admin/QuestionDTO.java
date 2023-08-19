package com.howthere.app.domain.admin;

import com.howthere.app.type.QuestionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class QuestionDTO {
    private Long id;
    private String oneToOneQuestionContent;
    private QuestionType oneToOneQuestionType;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
}
