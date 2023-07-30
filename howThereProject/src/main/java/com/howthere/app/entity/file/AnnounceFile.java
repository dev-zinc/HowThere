package com.howthere.app.entity.file;

import com.howthere.app.entity.Announcment;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ANNOUNCE_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnnounceFile extends FileEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Announcment announcment;
}
