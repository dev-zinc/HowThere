package com.howthere.app.domain.program;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component @Getter @Setter
@ToString
@NoArgsConstructor
public class ProgramMainDTO {
    private Long id;
    private String programAddress;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Integer programPrice;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;
}
