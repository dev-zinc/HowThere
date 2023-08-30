package com.howthere.app.domain.admin;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class AnnouncementDetailDTO {
    private String announcementTitle;
    private String announcementContent;
    private Long adminId;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private long fileSize;

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Builder
    public AnnouncementDetailDTO(String announcementTitle, String announcementContent, Long adminId, String fileName, String fileUuid, String filePath, long fileSize) {
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.adminId = adminId;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
