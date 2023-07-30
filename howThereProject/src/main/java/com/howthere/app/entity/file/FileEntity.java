package com.howthere.app.entity.file;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(schema = "TBL_FILE")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class FileEntity {
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String filePath;
    @NotNull private String fileUuid; //"0000-0000-0000-0000.png"
    @NotNull private String fileName;
    @NotNull private Long fileSize;
}
