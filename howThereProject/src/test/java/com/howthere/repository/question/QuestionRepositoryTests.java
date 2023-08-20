package com.howthere.repository.question;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.admin.QuestionRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.type.QuestionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = {HowThereApplication.class})
@Transactional
@Rollback(value = false)
@Slf4j
public class QuestionRepositoryTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        Question question = Question.builder()
                .oneToOneQuestionType(QuestionType.EVENT)
                .oneToOneQuestionContent("content")
                .member(member)
                .build();
        questionRepository.save(question);
    }

    @Test
    public void findAllTest() {
        questionRepository.findAll().stream().map(Question::toString).forEach(log::info);
    }

    @Test
    public void findAllWithKeywordTest() {
        Page<QuestionDTO> allWithKeyword = questionRepository.findAllWithKeyword(Pageable.ofSize(10), null);
        assertThat(allWithKeyword.getTotalElements()).isEqualTo(1);
    }
}
