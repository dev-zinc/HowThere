package com.howthere.app.entity.rent;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.file.RentCarFile;
import com.howthere.app.type.RentCarType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCar extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    private RentCarType rentCarType;
    private String rentCarName;
    private Integer rentCarPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentCarCompany rentCarCompany;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentCar",cascade = CascadeType.REMOVE)
    private List<RentCarFile> rentCarFiles = new ArrayList<>();

    @Builder
    public RentCar(Long id, RentCarType rentCarType, String rentCarName, Integer rentCarPrice, RentCarCompany rentCarCompany, List<RentCarFile> rentCarFiles) {
        this.id = id;
        this.rentCarType = rentCarType;
        this.rentCarName = rentCarName;
        this.rentCarPrice = rentCarPrice;
        this.rentCarCompany = rentCarCompany;
        this.rentCarFiles = rentCarFiles;
    }
}
