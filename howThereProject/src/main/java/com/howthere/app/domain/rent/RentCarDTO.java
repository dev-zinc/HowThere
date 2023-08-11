package com.howthere.app.domain.rent;

import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.type.RentCarType;
import lombok.Builder;
import lombok.ToString;

@ToString
public class RentCarDTO {

    private Long id;
    private RentCarType rentCarType;
    private String rentCarName;
    private Integer rentCarPrice;

    private RentCarCompany rentCarCompany;

    @Builder
    public RentCarDTO(Long id, RentCarType rentCarType, String rentCarName, Integer rentCarPrice, RentCarCompany rentCarCompany) {
        this.id = id;
        this.rentCarType = rentCarType;
        this.rentCarName = rentCarName;
        this.rentCarPrice = rentCarPrice;
        this.rentCarCompany = rentCarCompany;
    }
}
