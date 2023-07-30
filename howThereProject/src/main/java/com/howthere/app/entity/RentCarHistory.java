package com.howthere.app.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR_HISTORY")
public class RentCarHistory {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private Integer carRentTotalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentCar rentCar;

    //@ManyToOne
    //private Member member;
}
