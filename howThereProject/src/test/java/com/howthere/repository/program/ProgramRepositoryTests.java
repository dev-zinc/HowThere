package com.howthere.repository.program;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.type.MemberType;
import com.howthere.app.type.Verified;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional @Rollback(value = false)
public class ProgramRepositoryTests {
    @Autowired
    private ProgramRepository programRepository;

    @Test
    public void saveTest() {
        Member member = Member.builder()
                .memberName("name")
                .memberEmail("email")
                .memberBirthDate(LocalDateTime.now())
                .memberProfile("profile.png")
                .memberType(MemberType.MEMBER)
                .
                .build();

        House house = House.builder()
                .houseMaxHeadCount(10)
                .houseAddress("address")
                .houseLatitude(0.1)
                .houseLongitude(0.2)
                .houseTitle("title")
                .houseContent("content")
                .houseMaxPetCount(2)
                .member()
                .build();

        Program program = Program.builder()
                .programName("name")
                .programContent("content")
                .programPrice(100_000)
                .programStartDate(LocalDateTime.now())
                .programEndDate(LocalDateTime.now())
                .verified(Verified.Y)
                .house()
                .build();

        programRepository.save();
    }

    @Test
    public void findAllWithLimitTest() {
        programRepository.findAllWithLimit(Pageable.ofSize(10), "")
                .map(ProgramDTO::toString).forEach(log::info);
    }
}
