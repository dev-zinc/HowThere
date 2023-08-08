package com.howthere.app.repository.diary;

import com.howthere.app.domain.diary.DiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryQueryDSL {
    Page<DiaryDTO> findAllWithLimit(Pageable pageable, String keyword);
}
