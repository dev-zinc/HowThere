package com.howthere.app.repository.member;

import com.howthere.app.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDSL {
    public Optional<Member> findByMemberEmail(String memberEmail);
}
