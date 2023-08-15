package com.howthere.app.repository.admin;

import com.howthere.app.domain.admin.QuestionDTO;
import com.howthere.app.domain.admin.QuestionDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionQueryDSL {
    public Page<QuestionDTO> findMyQuestions(Long memberId, Pageable pageable);
    public QuestionDetailDTO findQnAById(Long id);
    public Page<QuestionDetailDTO> findQnAByMemberIdAndSearchText(String searchText, Pageable pageable);
}
