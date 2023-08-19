package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.domain.admin.QuestionDetailDTO;
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
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public QuestionDetailDTO findQnAByQuestionId(Long id) {
        return questionRepository.findQnAById(id);
    }

    @Override
    public Long questionSave(QuestionDTO dto) {
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
    public Page<QuestionDTO> getQuestions(Pageable pageable, String keyword) {
        return questionRepository.findAllWithKeyword(pageable, keyword);
    }

    @Override
    public QuestionDTO findQuestion(Long id) {
        return questionRepository.findDTOById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<QuestionDetailDTO> getQnAs(String searchText, Pageable pageable) {
        return null;
    }


}
