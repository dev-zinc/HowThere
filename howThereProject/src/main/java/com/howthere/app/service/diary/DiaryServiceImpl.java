package com.howthere.app.service.diary;

import com.howthere.app.entity.diary.Diary;
import com.howthere.app.repository.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    public Page<Diary> getList(Pageable pageable) {
        return diaryRepository.findAllWithPaging(pageable);
    }

}
