package com.howthere.app.repository.program;

import antlr.collections.impl.IntRange;
import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.type.Verified;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    public void findAllWithLimitTest() {
        Page<ProgramDTO> page = programRepository.findAllWithLimit(PageRequest.ofSize(10), null);

        assertThat(page.stream().count()).isEqualTo(10L);
    }
}
