package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnswerDTO;
import com.howthere.app.entity.admin.Answer;
import com.howthere.app.repository.admin.QuestionRepository;

public interface AnswerService {
    void save(AnswerDTO answerDTO);

    void modify(AnswerDTO answerDTO);

    default Answer toEntity(QuestionRepository questionRepository, AnswerDTO answerDTO) {
        return Answer.builder()
                .answerContent(answerDTO.getAnswerContent())
                .question(questionRepository.findById(answerDTO.getQuestionId()).orElseThrow(RuntimeException::new))
                .build();
    }

}
