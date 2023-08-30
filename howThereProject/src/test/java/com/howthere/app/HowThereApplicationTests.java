package com.howthere.app;

import com.howthere.app.embed.Address;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.admin.Question;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramReservation;
import com.howthere.app.repository.admin.AnnouncementRepository;
import com.howthere.app.repository.admin.QuestionRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.repository.program.ProgramReservationRepository;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import com.howthere.app.type.QuestionType;
import com.howthere.app.type.Verified;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Rollback(false)
@Transactional
class HowThereApplicationTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private ProgramReservationRepository programReservationRepository;

    @Value("${file-root}")
    String test;
    @Test
    void contextLoads() {
        log.info(test);
    }

    @Test
    public void saveDummyData() {
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

        memberRepository.findById(1L).ifPresent(member -> {
            IntStream.range(0, 100).forEach(i -> {
                House house = House.builder()
                        .member(member)
                        .houseTitle("title" + i)
                        .houseContent("content" + i)
                        .houseMaxHeadCount(i * 2)
                        .address(Address.builder()
                                .address("address")
                                .addressDetail("detail-" + i)
                                .longitude(i * 0.1)
                                .latitude(i * 0.3)
                                .build())
                        .houseMaxPetCount(i)
                        .build();
                houseRepository.save(house);
            });
        });

        Member member = memberRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        House house = houseRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);

        IntStream.range(0, 100).forEach(i -> {
            Program program = Program.builder()
                    .programName("name" + i)
                    .programContent("content" + i)
                    .programPrice(100_000 * i)
                    .programStartDate(LocalDate.from(LocalDateTime.now()))
                    .programEndDate(LocalDate.from(LocalDateTime.now()))
                    .verified(Verified.Y)
                    .house(house)
                    .build();
            programRepository.save(program);
        });

        Question question = Question.builder()
                .oneToOneQuestionType(QuestionType.EVENT)
                .oneToOneQuestionContent("content")
                .member(member)
                .build();
        questionRepository.save(question);

        IntStream.range(0, 50).forEach(i -> {
            Announcement announcement = Announcement.builder()
                    .announcementTitle("title" + i)
                    .announcementContent("content" + i)
                    .admin(member)
                    .build();
            announcementRepository.save(announcement);
        });

        List<Member> members = memberRepository.findAll();
        List<Program> programs = programRepository.findAll();

        IntStream.range(0, 50).forEach(i -> {
            Member host = members.stream().findAny().orElseThrow(RuntimeException::new);
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
