package com.howthere.app.entity.member;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class Member extends Period {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberName;
    private LocalDate memberBirthDate;
    private String memberProfile;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LoginType memberLoginType;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'MEMBER'")
    private MemberType memberType;

    private boolean deleted = Boolean.FALSE;

    public void setMemberBirthDate(LocalDate memberBirthDate) {
        this.memberBirthDate = memberBirthDate;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @Builder
    public Member(Long id, String memberEmail, String memberName, LocalDate memberBirthDate,
        String memberProfile, LoginType memberLoginType, MemberType memberType, boolean deleted) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberBirthDate = memberBirthDate;
        this.memberProfile = memberProfile;
        this.memberLoginType = memberLoginType;
        this.memberType = memberType;
        this.deleted = deleted;
    }
}
