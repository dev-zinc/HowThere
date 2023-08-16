package com.howthere.app.entity.program;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import com.howthere.app.type.Verified;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PROGRAM_RESERVATION")
@ToString
@Getter
public class ProgramReservation extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private Verified verified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @ToString.Exclude
    private Member host;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @ToString.Exclude
    private Program program;
}
