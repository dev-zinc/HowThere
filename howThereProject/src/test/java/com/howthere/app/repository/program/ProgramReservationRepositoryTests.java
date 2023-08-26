package com.howthere.app.repository.program;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramReservation;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.type.Verified;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(value = false)
public class ProgramReservationRepositoryTests {
    @Autowired
    private ProgramReservationRepository programReservationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProgramRepository programRepository;

    @Test
    public void saveTest() {
        List<Member> members = memberRepository.findAll();
        List<Program> programs = programRepository.findAll();

        IntStream.range(0, 50).forEach(i -> {
            Member host = members.stream().findAny().orElseThrow(RuntimeException::new);
            Member member = members.stream().findAny().orElseThrow(RuntimeException::new);
            Program program = programs.stream().findAny().orElseThrow(RuntimeException::new);
            ProgramReservation programReservation = ProgramReservation.builder()
                    .host(host)
                    .member(member)
                    .program(program)
                    .verified(Verified.N)
                    .build();
            programReservationRepository.save(programReservation);
        });
    }
}
