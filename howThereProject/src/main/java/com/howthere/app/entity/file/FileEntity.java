package com.howthere.app.entity.file;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(schema = "TBL_FILE")
@Getter @ToString @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
public class FileEntity {
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String filePath;
    @NotNull private String fileUuid; //"0000-0000-0000-0000.png"
    @NotNull private String fileName;
    @NotNull private Long fileSize;
}
