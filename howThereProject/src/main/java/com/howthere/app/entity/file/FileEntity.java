package com.howthere.app.entity.file;

import com.howthere.app.auditing.Period;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_FILE")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FileEntity extends Period {
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String filePath;
    @NotNull private String fileUuid; //"0000-0000-0000-0000.png"
    @NotNull private String fileName;
    @NotNull private Long fileSize;

    public FileEntity(String filePath, String fileUuid, String fileName, Long fileSize) {
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
}
