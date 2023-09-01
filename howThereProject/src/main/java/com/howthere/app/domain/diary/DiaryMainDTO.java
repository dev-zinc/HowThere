package com.howthere.app.domain.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class DiaryMainDTO {
    private Long id;
    private String diaryTitle;
    private LocalDateTime createdDate;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;
}
