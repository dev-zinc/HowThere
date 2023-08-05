package com.howthere.app.repository.diary;

import com.howthere.app.entity.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
