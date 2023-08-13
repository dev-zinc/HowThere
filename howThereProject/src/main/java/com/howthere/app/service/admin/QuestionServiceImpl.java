package com.howthere.app.service.admin;

import com.howthere.app.domain.QuestionDTO;
import com.howthere.app.domain.QuestionDetailDTO;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.repository.admin.AnswerRepository;
import com.howthere.app.repository.admin.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public QuestionDetailDTO findQnAByQuetionId(Long id) {
        return questionRepository.findQnAById(id);
    }

    @Override
    public Long qustionSave(QuestionDTO dto) {
        Question question = questionRepository.save(toEntity(dto));
        return question.getId();
    }

    @Override
    public void answerSave(QuestionDetailDTO dto) {
        answerRepository.save(toAnswer(dto));
    }

    @Override
    public Page<QuestionDTO> getMyQuestions(Pageable pageable, HttpSession session) {
        String temp = (String) session.getAttribute("id");
        Long id = null;
        if(temp != null){
            id = Long.parseLong(temp);
        }
        return questionRepository.findMyQuestions(id, pageable);
    }

    @Override
    public Page<QuestionDetailDTO> getQnAs(String searchText, Pageable pageable) {
        return null;
    }


}
