package com.howthere.repository;

import com.howthere.app.HowThereApplication;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional
@Rollback(value = false)
public class DiaryRepositoryTests {
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Test
    public void saveTest(){
//        final Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
//
//        Address address = Address.builder()
//                .address("서울시 강남구 대치동")
//                .addressDetail("123-4")
//                .latitude(123D)
//                .longitude(123D)
//                .build();
//
//        House house = House.builder()
//                .houseTitle("테스트 숙소1")
//                .houseContent("테스트 숙소 내용1")
//                .houseMaxHeadCount(3)
//                .houseMaxPetCount(1)
//                .member(member)
//                .address(address)
//                .build();
//
//        houseRepository.save(house);

        final Member member = memberRepository.findById(2L).orElseThrow(RuntimeException::new);
        final House house = houseRepository.findById(3L).orElseThrow(RuntimeException::new);

        for (int i=0; i<100; i++) {
            Diary diary = Diary.builder()
                    .diaryTitle("일기 제목 테스트" + (i + 1))
                    .diaryContent("일기 내용 테스트" + (i + 1))
                    .diaryViewCount(i)
                    .member(member)
                    .house(house)
                    .build();
            diaryRepository.save(diary);
        }
    }

    @Test
    public void findAllTest(){
        log.info(diaryRepository.findAll().toString());
    }

    @Test
    public void findAllWithPagingTest(){
//        컨트롤러에서 page - 1로 넣을 것! 지금은 그냥 0을 넣음 (1페이지)
        Page<Diary> diarysWithPaging = diaryRepository.findAllWithPaging(PageRequest.of(0, 10));
        log.info(diarysWithPaging.getContent().toString());
    }

    @Test
    public void updateByIdTest(){
        Optional<Diary> foundDiary = diaryRepository.findById(104L);
        foundDiary.ifPresent(diary -> {
            diary.setDiaryTitle("수정된 일기 제목");
            diaryRepository.update(diary);
        });

    }

}
