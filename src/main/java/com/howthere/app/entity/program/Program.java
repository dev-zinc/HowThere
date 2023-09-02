package com.howthere.app.entity.program;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.house.House;
import com.howthere.app.type.Verified;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Table(name = "TBL_PROGRAM")
@Entity
@ToString
@Getter
@DynamicInsert
@NoArgsConstructor
public class Program extends Period {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @NotNull
    private LocalDate programStartDate;
    @NotNull
    private LocalDate programEndDate;
    @NotNull
    private String programName;
    @NotNull
    private String programContent;
    @NotNull
    private Integer programPrice;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'N'")
    private Verified verified;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID")
    private House house;

    @OneToMany(mappedBy = "program", cascade = CascadeType.REMOVE)
    private List<ProgramReservation> reservations;

    @Builder
    public Program(LocalDate programStartDate, LocalDate programEndDate, String programName,
        String programContent, Integer programPrice, Verified verified, House house) {
        this.programStartDate = programStartDate;
        this.programEndDate = programEndDate;
        this.programName = programName;
        this.programContent = programContent;
        this.programPrice = programPrice;
        this.verified = verified;
        this.house = house;
    }

    public void setVerified(Verified verified) {
        this.verified = verified;
    }
}
