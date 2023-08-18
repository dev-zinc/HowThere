package com.howthere.app.domain.program;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class ProgramReservationDTO {
    private Long id;
    private Long programId;
    private Long houseId;
    private LocalDateTime reservedDate;
    private Long memberId;
    private Long hostId;
    private boolean isCancelled;

    @Builder
    public ProgramReservationDTO(Long programId, Long houseId, LocalDateTime reservedDate, Long memberId, Long hostId, boolean isCancelled) {
        this.programId = programId;
        this.houseId = houseId;
        this.reservedDate = reservedDate;
        this.memberId = memberId;
        this.hostId = hostId;
        this.isCancelled = isCancelled;
    }
}
