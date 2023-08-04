package com.howthere.app.entity.file;

import com.howthere.app.entity.Announcement;
import com.howthere.app.entity.Diary;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ANNOUNCE_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnounceFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Announcement announcement;

    @Builder
    public AnnounceFile(String filePath, String fileUuid, String fileName, Long fileSize, Announcement announcement) {
        super(filePath, fileUuid, fileName, fileSize);
        this.announcement = announcement;
    }
}
