package com.howthere.app.service.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
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
    public Slice<DiaryDTO> getListBySlice(Pageable pageable, String keyword) {
        return diaryRepository.findAllWithSlice(pageable, keyword);
    }

    @Override
    public void write(DiaryDTO diaryDTO) {
        diaryRepository.save(toEntity(diaryDTO));
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
        diaryRepository.updateViewCount(diaryRepository.findById(id).get());
    }

    @Override
    public void remove(Long id) {
        diaryRepository.deleteById(id);
    }

    public Diary toEntity(DiaryDTO diaryDTO){
        return Diary.builder()
                .id(diaryDTO.getId())
                .diaryTitle(diaryDTO.getDiaryTitle())
                .diaryContent(diaryDTO.getDiaryContent())
                .member(memberRepository.findById(diaryDTO.getMemberId()).get())
                .house(houseRepository.findById(diaryDTO.getHouseId()).get())
                .build();
    }
}
