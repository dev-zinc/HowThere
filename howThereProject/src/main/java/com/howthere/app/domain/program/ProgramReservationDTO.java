package com.howthere.app.domain.program;

import com.howthere.app.type.Verified;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
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
    private Long houseId;
    private String programContent;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Integer programPrice;
    private Long programId;
    private String filePath;

    @Builder
    public ProgramReservationDTO(Long id, String programName, String houseTitle,
        LocalDateTime reservedDate, Long memberId, Long hostId, Verified verified,
        String filePath) {
        this.id = id;
        this.programName = programName;
        this.houseTitle = houseTitle;
        this.reservedDate = reservedDate;
        this.memberId = memberId;
        this.hostId = hostId;
        this.verified = verified;
        this.filePath = filePath;
    }
}
