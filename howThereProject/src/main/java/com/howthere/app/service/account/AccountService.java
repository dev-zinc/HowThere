package com.howthere.app.service.account;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.entity.member.Member;

public interface AccountService {

    MemberDTO updateMyInfo(Long id, String name);


    default Member toEntity(MemberDTO memberDTO) {
        return Member.builder().id(memberDTO.getId())
            .memberEmail(memberDTO.getMemberEmail())
            .memberName(memberDTO.getMemberName())
            .memberBirthDate(memberDTO.getMemberBirthDate())
            .memberProfile(memberDTO.getMemberProfile())
            .memberLoginType(memberDTO.getMemberLoginType())
            .memberType(memberDTO.getMemberType())
            .build();

    }
}
