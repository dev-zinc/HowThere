package com.howthere.app.entity.program;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import com.howthere.app.type.Confirm;
import com.howthere.app.type.Verified;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "TBL_PROGRAM_RESERVATION")
@ToString
@Getter
@NoArgsConstructor
public class ProgramReservation extends Period {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private Verified verified;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private Confirm confirm;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @ToString.Exclude
    private Member host;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @ToString.Exclude
    private Program program;

    @Builder
    public ProgramReservation(Verified verified, Confirm confirm, Member member, Member host, Program program) {
        this.verified = verified;
        this.confirm = confirm;
        this.member = member;
        this.host = host;
        this.program = program;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }

    public void setConfirm(Confirm confirm) {
        this.confirm = confirm;
    }
}
