package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NotNull private String memberEmail;
    @NotNull private String memberName;
    @NotNull private LocalDateTime memberBirthDate;
    @NotNull private String memberProfile;
    @NotNull private LoginType memberLoginType;
    @NotNull private MemberType memberType;
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
