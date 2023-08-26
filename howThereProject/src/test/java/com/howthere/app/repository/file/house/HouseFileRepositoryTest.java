package com.howthere.app.repository.file.house;

import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
@RequiredArgsConstructor
class HouseFileRepositoryTest {

    @Autowired
    private HouseFileRepository fileRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test() {
        final Member member = Member.builder()
                .memberEmail("bcf@bcf.com")
                //.memberBirthDate(LocalDateTime.of(1990, 11, 11, 11, 11))
                .memberLoginType(LoginType.KAKAO)
                .memberName("LEE")
                .memberProfile("PRO")
                .memberType(MemberType.MEMBER)
                .build();
        memberRepository.save(member);
//        final Member member = memberRepository.findById(1L).get();

//        final Address address = Address.builder()
//                .latitude(127.0)
//                .longitude(35.0)
//                .addressDetail("상세주소")
//                .address("주소")
//                .build();
//        final House house = House.builder()
//                .houseContent("숙소소개글")
//                .houseTitle("금토일숙소")
//                .houseMaxHeadCount(4)
//                .houseMaxPetCount(2)
//                .address(address)
//                .member(member)
//                .build();
//        houseRepository.save(house);

//        final House house = houseRepository.findById(3L).get();
//        final HouseFile file = HouseFile.builder()
//                .filePath("/dev/" + house.getMember().getId())
//                .fileUuid(UUID.randomUUID().toString() + ".png")
//                .fileName("사진")
//                .fileSize(440L)
//                .house(house)
//                .thumb(true)
//                .build();
//        List<HouseFile> roomImgList = new ArrayList<>();
//        roomImgList.add(file);
//        for (int i = 0; i < 5; i++) {
//            final HouseFile roomImg = HouseFile.builder()
//                    .filePath("/dev/" + house.getMember().getId())
//                    .fileUuid(UUID.randomUUID().toString() + ".png")
//                    .fileName("사진 " + (i + 1))
//                    .fileSize(440L)
//                    .house(house)
//                    .build();
//            roomImgList.add(roomImg);
//        }

//        fileRepository.saveAll(roomImgList);
    }
}