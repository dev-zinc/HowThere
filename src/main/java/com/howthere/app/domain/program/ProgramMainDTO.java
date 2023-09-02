package com.howthere.app.domain.program;

import lombok.*;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component @Getter @Setter
@ToString
@NoArgsConstructor
public class ProgramMainDTO {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일");

    private Long id;
    private String programAddress;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Integer programPrice;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;

    public DateTimeFormatter format() {
        return DATE_TIME_FORMATTER;
    }

    public String getDottedPrice() {
        return new DecimalFormat("###,###").format(programPrice);
    }
}
