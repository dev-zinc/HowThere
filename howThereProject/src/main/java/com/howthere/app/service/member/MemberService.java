package com.howthere.app.service.member;

import com.howthere.app.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Page<Member> getMembers(Pageable pageable, String keyword);
}
