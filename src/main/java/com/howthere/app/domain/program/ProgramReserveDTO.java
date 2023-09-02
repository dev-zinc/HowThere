package com.howthere.app.domain.program;

import com.howthere.app.type.Verified;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class ProgramReserveDTO {
    private Long id;
    private Long programId;
    private String programName;
    private String houseTitle;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime reservedDate;
    private Long memberId;
    private Long hostId;
    private Long houseId;
    private Verified verified;

    @Builder
    public ProgramReserveDTO(String programName, Long programId, Long houseId, String houseTitle, LocalDateTime reservedDate, Long memberId, Long hostId, Verified verified) {
        this.programName = programName;
        this.programId = programId;
        this.houseId = houseId;
        this.houseTitle = houseTitle;
        this.reservedDate = reservedDate;
        this.memberId = memberId;
        this.hostId = hostId;
        this.verified = verified;
    }
}
