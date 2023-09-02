package com.howthere.app.repository.diary;

import com.howthere.app.HowThereApplication;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        final Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);
        final List<House> houses = houseRepository.findAll();
        List<String> titles = Arrays.asList("");
        List<String> Contents = Arrays.asList("");
        
//        houses.forEach(house -> {
//            Diary diary = Diary.builder()
//                    .diaryTitle("title" + (i + 1))
//                    .diaryContent("content" + (i + 1))
//                    .member(member)
//                    .house(house)
//                    .build();
//            diaryRepository.save(diary);
//        });
    }

    @Test
    public void findAllTest(){
        log.info(diaryRepository.findAll().toString());
    }

    @Test
    public void findAllWithPagingTest(){
//        컨트롤러에서 page - 1로 넣을 것! 지금은 그냥 0을 넣음 (1페이지)
//        Page<Diary> diarysWithPaging = diaryRepository.findAllWithPaging(PageRequest.of(0, 10));
//        log.info(diarysWithPaging.getContent().toString());
//        Page<Diary> diarysWithPaging = diaryService.getList(PageRequest.of(0, 10));
//        log.info(diarysWithPaging.getContent().toString());
    }

    @Test
    public void updateByIdTest(){
        Optional<Diary> foundDiary = diaryRepository.findById(104L);
        foundDiary.ifPresent(diary -> diaryRepository.update(diary));

    }


    @Test
    public  void updateViewCountTest(){
        Optional<Diary> foundDiary = diaryRepository.findById(154L);
        foundDiary.ifPresent(diary -> diaryRepository.updateViewCount(diary));
    }
}
