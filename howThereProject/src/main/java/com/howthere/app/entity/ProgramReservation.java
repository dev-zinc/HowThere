package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.VeriFied;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PROGRAM_RESERVATION")
public class ProgramReservation extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private VeriFied veriFied;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;
}
