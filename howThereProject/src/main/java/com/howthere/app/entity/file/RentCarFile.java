package com.howthere.app.entity.file;

import com.howthere.app.entity.rentCar.RentCar;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_RENT_CAR_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCarFile extends FileEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private RentCar rentCar;

    @Builder
    public RentCarFile(String filePath, String fileUuid, String fileName, Long fileSize, RentCar rentCar) {
        super(filePath, fileUuid, fileName, fileSize);
        this.rentCar = rentCar;
    }
}
