package com.howthere.app.domain.admin;

import com.howthere.app.auditing.Period;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String announcementTitle;
    private String announcementContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public AnnouncementDTO(Long id, String announcementTitle, String announcementContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
