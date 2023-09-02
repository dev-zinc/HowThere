package com.howthere.app.service.member;

import com.howthere.app.domain.member.MemberInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    Page<MemberInfoDTO> getMembers(Pageable pageable, String keyword);

    void modifyAllActivationById(List<Long> ids);
}
