package com.howthere.app.entity.file;

import com.howthere.app.entity.Diary;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
