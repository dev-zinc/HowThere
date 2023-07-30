package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.CancelType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TBL_PROGRAM_PAYMENT")
@Entity
@Getter
@ToString
@DynamicInsert
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
}
