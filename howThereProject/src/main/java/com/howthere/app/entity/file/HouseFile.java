package com.howthere.app.entity.file;

import com.howthere.app.entity.house.House;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_HOUSE_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "HOUSE_ID")
    private House house;
    private boolean thumb = Boolean.FALSE;

    @Builder
    public HouseFile(String filePath, String fileUuid, String fileName, Long fileSize, House house) {
        super(filePath, fileUuid, fileName, fileSize);
        this.house = house;
    }
}
