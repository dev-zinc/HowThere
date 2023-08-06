package com.howthere.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String announcementTitle;
    private String announcementContent;

    @Builder
    public AnnouncementDTO(Long id, String announcementTitle, String announcementContent) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
    }
}
