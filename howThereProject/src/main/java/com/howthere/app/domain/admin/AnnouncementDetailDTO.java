package com.howthere.app.domain.admin;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class AnnouncementDetailDTO {
    private Long id;
    private String announcementTitle;
    private String announcementContent;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime updatedDate;
    private Long adminId;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;

    public String getFormattedCreatedDate() {
        return createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Builder
    public AnnouncementDetailDTO(Long id, String announcementTitle, String announcementContent, LocalDateTime createdDate,
                                 LocalDateTime updatedDate, Long adminId, String fileName, String fileUuid, String filePath, long fileSize) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.adminId = adminId;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
