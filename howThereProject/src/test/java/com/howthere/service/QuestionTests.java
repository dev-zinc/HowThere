package com.howthere.service;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.QuestionDTO;
import com.howthere.app.domain.QuestionDetailDTO;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.repository.admin.QuestionRepository;
import com.howthere.app.service.admin.QuestionService;
import com.howthere.app.type.QuestionType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        QuestionDTO dto = new QuestionDTO();
        dto.setOneToOneQuestionContent("content test");
        dto.setOneToOneQuestionType(QuestionType.EVENT);
        Question question = questionService.toEntity(dto);
        questionRepository.save(question);
    }

    @Test
    public void findQnAByIdTest(){
        QuestionDetailDTO dto = questionRepository.findQnAById(204L);
        log.info(dto.toString());
    }
}
