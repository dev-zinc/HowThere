package com.howthere.app.repository.admin;

import com.howthere.app.entity.admin.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
