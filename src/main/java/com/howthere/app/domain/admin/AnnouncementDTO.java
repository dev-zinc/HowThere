package com.howthere.app.domain.admin;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String announcementTitle;
    private String announcementContent;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime updatedDate;
    private Long adminId;

    @Builder
    public AnnouncementDTO(Long id, String announcementTitle, String announcementContent,
                           LocalDateTime createdDate, LocalDateTime updatedDate, Long adminId) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.adminId = adminId;
    }
}
