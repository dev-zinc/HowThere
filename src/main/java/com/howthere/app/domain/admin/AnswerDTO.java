package com.howthere.app.domain.admin;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter @Setter @ToString
@Component
@RequiredArgsConstructor
public class AnswerDTO {
    private Long id;
    private Long questionId;
    private String answerContent;

    @Builder
    public AnswerDTO(Long id, String answerContent, Long questionId) {
        this.id = id;
        this.answerContent = answerContent;
        this.questionId = questionId;
    }
}
