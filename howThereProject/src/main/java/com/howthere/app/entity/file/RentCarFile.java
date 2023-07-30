package com.howthere.app.entity.file;

import com.howthere.app.entity.RentCar;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "TBL_RENT_CAR_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCarFile extends FileEntity {
//    @ManyToOne(fetch = FetchType.LAZY)
//    private RentCar rentCar;
}
