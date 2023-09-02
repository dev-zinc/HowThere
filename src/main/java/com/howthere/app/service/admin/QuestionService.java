package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.admin.QuestionDetailDTO;
import com.howthere.app.entity.admin.Answer;
import com.howthere.app.entity.admin.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QuestionService {

    Question findById(Long id);
    QuestionDetailDTO findQnAByQuestionId(Long id);

    Long questionSave(QuestionDTO dto);

    void answerSave(QuestionDetailDTO dto);

    Page<QuestionDTO> getMyQuestions(Pageable pageable, HttpSession session);

    Page<QuestionDTO> getQuestions(Pageable pageable, String keyword);

    QuestionDTO findQuestion(Long id);

    Page<QuestionDetailDTO> getQnAs(String searchText, Pageable pageable);

    void deleteAllBy(List<Long> ids);

    default Question toEntity(QuestionDTO dto){
        return Question.builder()
                .id(dto.getId())
                .oneToOneQuestionContent(dto.getOneToOneQuestionContent())
                .oneToOneQuestionType(dto.getOneToOneQuestionType())
                .build();
    }

    default Question toEntity(QuestionDetailDTO dto){
        return Question.builder()
                .id(dto.getId())
                .oneToOneQuestionType(dto.getOneToOneQuestionType())
                .oneToOneQuestionContent(dto.getOneToOneQuestionContent())
                .build();
    }

    default Answer toAnswer(QuestionDetailDTO dto){
        return Answer.builder()
                .id(dto.getAnswerId())
                .answerContent(dto.getAnswerContent())
                .question(toEntity(dto))
                .build();
    }
}
