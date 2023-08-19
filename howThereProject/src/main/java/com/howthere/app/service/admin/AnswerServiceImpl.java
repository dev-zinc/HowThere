package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnswerDTO;
import com.howthere.app.repository.admin.AnswerRepository;
import com.howthere.app.repository.admin.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void save(AnswerDTO answerDTO) {
        answerRepository.save(toEntity(questionRepository, answerDTO));
    }
}
