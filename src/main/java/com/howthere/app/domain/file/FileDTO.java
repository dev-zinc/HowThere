package com.howthere.app.domain.file;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
@NoArgsConstructor
public class FileDTO {
    private String filePath;
    private String fileUuid;
    private String fileName;
    private Long fileSize;

    public String getFullName() {
        return fileUuid + "_" + fileName;
    }

    @Builder
    public FileDTO(String filePath, String fileUuid, String fileName, Long fileSize) {
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
}
