package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.rentCarType.RentCarType;
import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR")
@SQLDelete(sql = "UPDATE TBL_RENT_CAR SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCar extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    private RentCarType rentCarType;
    private String rentCarName;
    private Integer rentCarPrice;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private RentCarCompany rentCarCompany;

    @Builder
    public   RentCar(RentCarType rentCarType, String rentCarName, Integer rentCarPrice, RentCarCompany rentCarCompany) {
        this.rentCarType = rentCarType;
        this.rentCarName = rentCarName;
        this.rentCarPrice = rentCarPrice;
        this.rentCarCompany = rentCarCompany;
    }
}
