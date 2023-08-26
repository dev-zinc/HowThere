package com.howthere.app.repository.member;

import com.howthere.app.domain.member.MemberInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDSL {
    Page<MemberInfoDTO> getMembers(Pageable pageable, String keyword);
}
