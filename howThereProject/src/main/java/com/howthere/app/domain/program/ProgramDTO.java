package com.howthere.app.domain.program;

import com.howthere.app.type.Verified;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ProgramDTO {
    private Long id;
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    private String programAddress;
    private String programName;
    private String programContent;
    private Verified verified;

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
