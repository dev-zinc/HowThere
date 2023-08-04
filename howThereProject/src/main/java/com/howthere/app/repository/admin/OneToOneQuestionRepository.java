package com.howthere.app.repository.admin;

import com.howthere.app.entity.OneToOneQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneToOneQuestionRepository extends JpaRepository<OneToOneQuestion, Long> {
}
