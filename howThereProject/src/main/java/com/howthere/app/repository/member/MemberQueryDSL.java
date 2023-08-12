package com.howthere.app.repository.member;

import com.howthere.app.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDSL {
    Page<Member> getMembers(Pageable pageable, String keyword);
}
