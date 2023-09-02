package com.howthere.app.repository.program;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.program.ProgramListDTO;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.repository.file.FileRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.type.Verified;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional @Rollback(value = false)
public class ProgramRepositoryTests {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private FileRepository fileRepository;

    @Test
    public void saveTest() {
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
    }

    @Test
    public void detailedSaveTest() {
        House house = houseRepository.findAll().stream().findFirst().orElseThrow(RuntimeException::new);
        
        Program program = Program.builder()
                .programName("테스트 프로그램")
                .programContent("테스트 내용")
                .programPrice(1_000_000)
                .programStartDate(LocalDate.of(2023, 9, 3))
                .programEndDate(LocalDate.of(2023, 9, 27))
                .verified(Verified.Y)
                .house(house)
                .build();
        
        programRepository.save(program);
    }

    @Test
    public void findAllWithLimitTest() {
        Page<ProgramListDTO> page = programRepository.findAllWithLimit(PageRequest.ofSize(10), null);

        assertThat(page.stream().count()).isEqualTo(10L);
    }
}
