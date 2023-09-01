package com.howthere.app.domain.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class DiaryMainDTO {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    private Long id;
    private String diaryTitle;
    private LocalDateTime createdDate;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;

    public DateTimeFormatter format() {
        return DATE_TIME_FORMATTER;
    }
}
