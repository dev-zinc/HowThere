package com.howthere.app.entity.file;

import com.howthere.app.entity.rent.RentCar;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RENT_CAR_FILE")
@Getter @ToString(callSuper = true, exclude = {"rentCar"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCarFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "RENT_CAR_ID")
    private RentCar rentCar;

    @Builder
    public RentCarFile(String filePath, String fileUuid, String fileName, Long fileSize, RentCar rentCar) {
        super(filePath, fileUuid, fileName, fileSize);
        this.rentCar = rentCar;
    }
}
