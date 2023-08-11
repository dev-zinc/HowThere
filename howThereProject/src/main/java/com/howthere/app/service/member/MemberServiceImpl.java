package com.howthere.app.service.member;

import com.howthere.app.entity.member.Member;
import com.howthere.app.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Page<Member> getMembers(Pageable pageable, String keyword) {
        return memberRepository.getMembers(pageable, keyword);
    }
}
