package com.howthere.app.entity.file;

import com.howthere.app.entity.admin.Announcement;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ANNOUNCE_FILE")
@Getter
@ToString
@NoArgsConstructor
public class AnnounceFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "ANNOUNCEMENT_ID")
    private Announcement announcement;

    @Builder
    public AnnounceFile(String filePath, String fileUuid, String fileName, Long fileSize, Announcement announcement) {
        super(filePath, fileUuid, fileName, fileSize);
        this.announcement = announcement;
    }
}
