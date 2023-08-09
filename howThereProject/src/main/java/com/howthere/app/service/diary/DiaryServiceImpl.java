package com.howthere.app.service.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.repository.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Override
    public Page<Diary> getList(Pageable pageable) {
        return diaryRepository.findAllWithPaging(pageable);
    }

    @Override
    public Slice<Diary> getListBySlice(Pageable pageable) {
        return diaryRepository.findAllWithSlice(pageable);
    }

    @Override
    public void write(DiaryDTO diaryDTO) {
        diaryRepository.save(toEntity(diaryDTO));
    }

}
