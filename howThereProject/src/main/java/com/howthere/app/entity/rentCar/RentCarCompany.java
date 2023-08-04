package com.howthere.app.entity.rentCar;

import com.howthere.app.auditing.Period;
import com.howthere.app.embed.Address;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RENT_CAR_COMPANY")
@Getter @ToString
@NoArgsConstructor
public class RentCarCompany extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String RentCarCompanyName;
    @Embedded
    private Address RentCarCompanyAddress;

    @Builder
    public RentCarCompany(String rentCarCompanyName, Address rentCarCompanyAddress) {
        RentCarCompanyName = rentCarCompanyName;
        RentCarCompanyAddress = rentCarCompanyAddress;
    }
}