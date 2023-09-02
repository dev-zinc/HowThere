package com.howthere.app.repository.diary.reply;

import com.howthere.app.entity.diary.DiaryReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryReplyRepository extends JpaRepository<DiaryReply, Long>, DiaryReplyQueryDSL {
}
