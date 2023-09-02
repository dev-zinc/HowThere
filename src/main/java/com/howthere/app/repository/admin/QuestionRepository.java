package com.howthere.app.repository.admin;

import com.howthere.app.entity.admin.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionQueryDSL {
}
