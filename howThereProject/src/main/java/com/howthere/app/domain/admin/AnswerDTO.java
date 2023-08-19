package com.howthere.app.domain.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@RequiredArgsConstructor
public class AnswerDTO {
    private String content;
    private Long questionId;

    @Builder
    public AnswerDTO(String content, Long questionId) {
        this.content = content;
        this.questionId = questionId;
    }
}
