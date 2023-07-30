package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.VeriFied;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Table(name = "TBL_PROGRAM")
@Entity
@ToString
@Getter
@DynamicInsert
public class Program extends Period {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDateTime programStartDate;
    @NotNull
    private LocalDateTime programEndDate;
    private String programName;
    private String programContent;
    @NotNull
    private Integer programPrice;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private VeriFied veriFied;
}
