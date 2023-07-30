package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE ,mappedBy = "rentCarCompany")
    private List<RentCar> rentCar = new ArrayList<>();

    public void setCarRegistration(RentCar rentCar){
        if(rentCar.getRentCarCompany() != this){
            rentCar.setRentCarCompany(this);
        }
        this.getRentCar().add(rentCar);
    }

}