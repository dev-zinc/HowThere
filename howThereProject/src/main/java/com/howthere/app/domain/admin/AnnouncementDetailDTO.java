package com.howthere.app.domain.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
@RequiredArgsConstructor
public class AnnouncementDetailDTO {
    private String announcementTitle;
    private String announcementContent;
    private Long fileId;
}
