package com.howthere.app.entity;

import com.howthere.app.audit.Period;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(schema = "TBL_MEMBER")
@Getter @ToString(callSuper = true, exclude = {})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE TBL_MEMBER SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberName;
    private LocalDateTime memberBirthDate;
    private String memberProfile;
    private LoginType memberLoginType;
    private MemberType memberType;
    private boolean deleted = Boolean.FALSE;

    @Builder
    public Member(String memberEmail, String memberName, LocalDateTime memberBirthDate, String memberProfile, LoginType memberLoginType, MemberType memberType) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberBirthDate = memberBirthDate;
        this.memberProfile = memberProfile;
        this.memberLoginType = memberLoginType;
        this.memberType = memberType;
    }
}
