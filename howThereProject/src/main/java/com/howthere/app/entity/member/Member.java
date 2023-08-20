package com.howthere.app.entity.member;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "TBL_MEMBER")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@SQLDelete(sql = "UPDATE TBL_MEMBER SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberName;
    private LocalDate memberBirthDate;
    private String memberProfile;

    @Enumerated(EnumType.STRING)
    @NotNull private LoginType memberLoginType;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMBER'")
    private MemberType memberType;

    private boolean deleted = Boolean.FALSE;

    @Builder
    public Member(String memberEmail, String memberName, LocalDate memberBirthDate, String memberProfile, LoginType memberLoginType, MemberType memberType) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberBirthDate = memberBirthDate;
        this.memberProfile = memberProfile;
        this.memberLoginType = memberLoginType;
        this.memberType = memberType;
    }
}
