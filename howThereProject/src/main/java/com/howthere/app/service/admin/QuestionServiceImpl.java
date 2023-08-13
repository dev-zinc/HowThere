package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.QuestionDetailDTO;
import com.howthere.app.repository.admin.AnswerRepository;
import com.howthere.app.repository.admin.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void answerSave(QuestionDetailDTO dto) {
        answerRepository.save(toAnswer(dto));
    }
}
