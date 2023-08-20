package com.howthere.repository.program;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(value = false)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
        IntStream.range(0, 10).forEach(i -> {
            Member member = Member.builder()
                    .memberName("name" + i)
                    .memberEmail(i + "email@gmail.com")
                    .memberProfile(i+".png")
                    .memberType(MemberType.MEMBER)
                    .memberBirthDate(LocalDate.now().plusDays(i))
                    .memberLoginType(LoginType.KAKAO)
                    .build();
            memberRepository.save(member);
        });
    }
}
