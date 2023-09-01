package com.howthere.app.entity.rent;

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
    private String rentCarCompanyName;
    @Embedded
    private Address rentCarCompanyAddress;

    @Builder
    public RentCarCompany(String rentCarCompanyName, Address rentCarCompanyAddress) {
        this.rentCarCompanyName = rentCarCompanyName;
        this.rentCarCompanyAddress = rentCarCompanyAddress;
    }
}