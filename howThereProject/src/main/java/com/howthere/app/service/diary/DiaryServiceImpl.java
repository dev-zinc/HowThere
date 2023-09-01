package com.howthere.app.service.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.diary.DiaryMainDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.diary.DiaryRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final HouseRepository houseRepository;

//    @Override
//    public Page<Diary> getList(Pageable pageable) {
//        return diaryRepository.findAllWithPaging(pageable);
//    }

    @Override
    public Slice<DiaryDTO> getListBySlice(Pageable pageable, String keyword, String order) {
        return diaryRepository.findAllWithSlice(pageable, keyword, order);
    }

    @Override
    public List<DiaryMainDTO> getList() {
        return diaryRepository.findAll10();
    }

//    @Override
//    public void write(DiaryDTO diaryDTO) {
//        diaryRepository.save(toEntity(diaryDTO));
//    }

    @Override
    public Long write(DiaryDTO diaryDTO) {
        Member member = memberRepository.findById(diaryDTO.getMemberId()).orElseThrow();
        House house = houseRepository.findById(diaryDTO.getHouseId()).orElseThrow();
        Diary diary = diaryRepository.save(toEntity(diaryDTO, member, house));

        return diary.getId();
    }

    @Override
    public Long getDiaryId() {
        return diaryRepository.findId();
    }

    @Override
    public Optional<Diary> getDiary(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public void update(DiaryDTO diaryDTO) {
        diaryRepository.update(toEntity(diaryDTO));
    }

    @Override
    public void updateViewCount(Long id) {
        diaryRepository.updateViewCount(diaryRepository.findById(id).orElseThrow());
    }

    @Override
    public void remove(Long id) {
        diaryRepository.deleteById(id);
    }

//    public Diary toEntity(DiaryDTO diaryDTO){
//        return Diary.builder()
//                .id(diaryDTO.getId())
//                .diaryTitle(diaryDTO.getDiaryTitle())
//                .diaryContent(diaryDTO.getDiaryContent())
//                .member(memberRepository.findById(diaryDTO.getMemberId()).get())
//                .house(houseRepository.findById(diaryDTO.getHouseId()).get())
//                .build();
//    }
}
