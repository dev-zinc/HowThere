package com.howthere.app.repository.diary.like;

import com.howthere.app.entity.diary.DiaryLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Long>, DiaryLikeQueryDSL {
}
