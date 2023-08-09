package com.howthere.app.service.diary;

import com.howthere.app.entity.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DiaryService {
    public Page<Diary> getList(Pageable pageable);
}
