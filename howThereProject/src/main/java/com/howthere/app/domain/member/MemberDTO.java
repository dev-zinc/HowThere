package com.howthere.app.domain.member;

import com.howthere.app.entity.member.Member;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class MemberDTO implements Serializable {
    private Long id;
    private String memberEmail;
    private String memberName;
    private LocalDate memberBirthDate;
    private String memberProfile;
    private LoginType memberLoginType;
    private MemberType memberType;

    @Builder
    public MemberDTO(Long id, String memberEmail, String memberName, LocalDate memberBirthDate, String memberProfile, LoginType memberLoginType, MemberType memberType) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberBirthDate = memberBirthDate;
        this.memberProfile = memberProfile;
        this.memberLoginType = memberLoginType;
        this.memberType = memberType;
    }

    public MemberDTO(Member member){
        this.id = member.getId();
        this.memberEmail = member.getMemberEmail();
        this.memberName = member.getMemberName();
        this.memberBirthDate = member.getMemberBirthDate();
        this.memberProfile = member.getMemberProfile();
        this.memberLoginType = member.getMemberLoginType();
        this.memberType = member.getMemberType();
    }
}
