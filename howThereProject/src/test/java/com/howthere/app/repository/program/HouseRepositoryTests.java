package com.howthere.app.repository.program;

import com.howthere.app.HowThereApplication;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.house.House;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest(classes = {HowThereApplication.class})
@Transactional
@Rollback(false)
@Slf4j
public class HouseRepositoryTests {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest() {
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
    }

    @Test
    public void findAllTest() {
        houseRepository.findAll().forEach(house -> log.info(house.toString()));
    }
}
