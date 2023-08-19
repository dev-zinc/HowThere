package com.howthere.app.domain.program;

import com.howthere.app.type.Verified;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class ProgramReservationDTO {
    private Long id;
    private String programName;
    private String houseTitle;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime reservedDate;
    private Long memberId;
    private Long hostId;
    private Verified verified;

    @Builder
    public ProgramReservationDTO(String programName, String houseTitle, LocalDateTime reservedDate, Long memberId, Long hostId, Verified verified) {
        this.programName = programName;
        this.houseTitle = houseTitle;
        this.reservedDate = reservedDate;
        this.memberId = memberId;
        this.hostId = hostId;
        this.verified = verified;
    }
}
