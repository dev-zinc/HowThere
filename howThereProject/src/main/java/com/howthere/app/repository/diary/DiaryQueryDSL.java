package com.howthere.app.repository.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryQueryDSL {
//    일기 목록
    public List<Diary> findAll();

//    일기 목록 페이징 처리
    public Page<Diary> findAllWithPaging(Pageable pageable);

//    일기 상세보기

//    일기 수정
    public void update(Diary diary);
}
