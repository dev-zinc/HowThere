package com.howthere.app.domain.program;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProgramPaymentDTO {

    private Integer paymentPrice;
    private String programName;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Long programId;

    @Builder
    public ProgramPaymentDTO(Integer paymentPrice, String programName, LocalDate programStartDate,
        LocalDate programEndDate, Long programId) {
        this.paymentPrice = paymentPrice;
        this.programName = programName;
        this.programStartDate = programStartDate;
        this.programEndDate = programEndDate;
        this.programId = programId;
    }
}
