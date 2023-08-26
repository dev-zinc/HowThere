package com.howthere.app.repository.member;

import com.howthere.app.domain.member.MemberInfoDTO;
import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDSL {
    Page<MemberInfoDTO> getMemberInfoDTOs(Pageable pageable, String keyword);
    Page<Member> getMembers(Pageable pageable, String keyword);

    MemberDTO findByIdToMemberDto(Long id);
}
