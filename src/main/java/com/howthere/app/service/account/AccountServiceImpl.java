package com.howthere.app.service.account;

import com.howthere.app.domain.member.MemberDTO;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class AccountServiceImpl implements AccountService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO updateMyInfo(Long id, String name) {
        final MemberDTO member = memberRepository.findByIdToMemberDto(id);
        member.setMemberName(name);
        memberRepository.save(toEntity(member));
        return member;
    }
}
