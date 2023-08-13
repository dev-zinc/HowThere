package com.howthere.service;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.QuestionDTO;
import com.howthere.app.domain.QuestionDetailDTO;
import com.howthere.app.entity.admin.Answer;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.repository.admin.AnswerRepository;
import com.howthere.app.repository.admin.QuestionRepository;
import com.howthere.app.service.admin.QuestionService;
import com.howthere.app.type.QuestionType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest(classes = HowThereApplication.class)
@Transactional
@Rollback(false)
@Slf4j
public class QuestionTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;

    @Test
    public void saveTest(){
        for (int i = 1; i <= 100; i++) {
            QuestionDTO dto = new QuestionDTO();
            dto.setOneToOneQuestionContent("content test" + i);
            dto.setOneToOneQuestionType(QuestionType.EVENT);
            Question question = questionService.toEntity(dto);
            questionRepository.save(question);
        }
    }

    @Test
    public void findQnAByIdTest(){
        QuestionDetailDTO dto = questionRepository.findQnAById(204L);
        log.info(dto.toString());
    }

    @Test
    public void answerSaveTest(){
        Question question = questionRepository.findById(306L).orElseThrow(RuntimeException::new);
        QuestionDetailDTO detailDTO = QuestionDetailDTO.builder()
                .id(question.getId())
                .answerContent("대답이야")
                .oneToOneQuestionContent(question.getOneToOneQuestionContent())
                .oneToOneQuestionType(question.getOneToOneQuestionType())
                .build();
        questionService.answerSave(detailDTO);
    }

    @Test
    public void findQnAByMemberIdAndSearchTextTest(){
        PageRequest pageRequest = PageRequest.of(1, 10);
        Page<QuestionDetailDTO> qnas = questionRepository.findQnAByMemberIdAndSearchText(null, pageRequest);
        log.info("page : {}", qnas.toString());
        qnas.getContent().forEach(qna -> log.info(qna.toString()));
    }
}
