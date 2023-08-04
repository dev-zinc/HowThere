package com.howthere.app.entity.rentCar;

import com.howthere.app.auditing.Period;
import com.howthere.app.type.RentCarType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"rentCarCompany"})
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


    @Builder
    public RentCar(RentCarType rentCarType, String rentCarName, Integer rentCarPrice, RentCarCompany rentCarCompany) {
        this.rentCarType = rentCarType;
        this.rentCarName = rentCarName;
        this.rentCarPrice = rentCarPrice;
        this.rentCarCompany = rentCarCompany;
    }
}
