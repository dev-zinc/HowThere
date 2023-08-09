package com.howthere.app.repository.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface DiaryQueryDSL {
//    일기 목록
    public List<Diary> findAll();

//    일기 목록 페이징 처리
    public Page<Diary> findAllWithPaging(Pageable pageable);

//    일기 목록 더보기, 무한 스크롤 처리
    public Slice<DiaryDTO> findAllWithSlice(Pageable pageable);

//    일기 상세보기
    public Optional<DiaryDTO> findById_QueryDSL(Long id);

//    일기 수정
    public void update(Diary diary);
}
