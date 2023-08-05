package com.howthere.app.entity.program;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import com.howthere.app.type.CancelType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TBL_PROGRAM_PAYMENT")
@Entity
@Getter
@ToString
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProgramPayment extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private Integer paymentPrice;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private CancelType canceled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    @ToString.Exclude
    private Program program;
}
