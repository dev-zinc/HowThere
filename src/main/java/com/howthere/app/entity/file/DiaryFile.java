package com.howthere.app.entity.file;

import com.howthere.app.entity.diary.Diary;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DIARY_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    @Builder
    public DiaryFile(String filePath, String fileUuid, String fileName, Long fileSize, Diary diary) {
        super(filePath, fileUuid, fileName, fileSize);
        this.diary = diary;
    }
}
