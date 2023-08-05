package com.howthere.app.entity.file;

import com.howthere.app.entity.diary.Diary;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_DIARY_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public DiaryFile(String filePath, String fileUuid, String fileName, Long fileSize, Diary diary) {
        super(filePath, fileUuid, fileName, fileSize);
        this.diary = diary;
    }
}
