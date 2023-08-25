package com.howthere.app.domain.program;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.howthere.app.type.Verified;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class ProgramDTO {
    private Long id;
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime endDate;
    private String programAddress;
    private String programName;
    private String programContent;
    private Integer programPrice;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Long houseId;
    private Verified verified;
    private String thumbnail;

    @Builder
    public ProgramDTO(Long memberId, LocalDateTime createdDate, String programAddress, String programName,
                      String programContent, Verified verified) {
        this.memberId = memberId;
        this.createdDate = createdDate;
        this.programAddress = programAddress;
        this.programName = programName;
        this.programContent = programContent;
        this.verified = verified;
    }
}
