package com.howthere.app.repository.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.domain.admin.AnnouncementDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementQueryDSL {
    // TODO: 2023-08-05 페이징 처리해서 조회(최신순)
    public Page<AnnouncementDTO> findAllQueryDSL(Pageable pageable, String keyword);
}
