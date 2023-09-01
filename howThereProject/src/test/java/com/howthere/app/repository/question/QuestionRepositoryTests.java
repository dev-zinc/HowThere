package com.howthere.app.repository.question;

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

import java.util.Arrays;
import java.util.stream.IntStream;

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
        Member member = memberRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        IntStream.range(0, 50).forEach(i -> {
            Question question = Question.builder()
                    .oneToOneQuestionType(QuestionType.EVENT)
                    .oneToOneQuestionContent("content" + i)
                    .member(member)
                    .build();
            questionRepository.save(question);
        });
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

    @Test
    public void testData(){
        Member member = Member.builder()
                .id(1L)
                .build();
        Question question1 = Question.builder()
                .oneToOneQuestionContent("이벤트 언제 시작하는 거에요?")
                .oneToOneQuestionType(QuestionType.EVENT)
                .member(member)
                .build();
        Question question2 = Question.builder()
                .oneToOneQuestionContent("제가 활동을 이렇게 많이 했는데 다른 사람들이랑 똑같이 대우 받는게 너무 기분이 안좋네요.")
                .oneToOneQuestionType(QuestionType.MEMBER)
                .member(member)
                .build();
        Question question3 = Question.builder()
                .oneToOneQuestionContent("활동 많이하면 혜택이 있나요?")
                .oneToOneQuestionType(QuestionType.BENEFIT)
                .member(member)
                .build();
        Question question4 = Question.builder()
                .oneToOneQuestionContent("예약한거 취소좀 해주세요.")
                .oneToOneQuestionType(QuestionType.CANCEL)
                .member(member)
                .build();
        Question question5 = Question.builder()
                .oneToOneQuestionContent("결제는 끝났는데 다른 카드로 다시 결재할 수있나요?")
                .oneToOneQuestionType(QuestionType.PAY)
                .member(member)
                .build();
        Question question6 = Question.builder()
                .oneToOneQuestionContent("이용 꿀팁좀여~")
                .oneToOneQuestionType(QuestionType.USE)
                .member(member)
                .build();
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);
        questionRepository.save(question5);
        questionRepository.save(question6);
    }
}
