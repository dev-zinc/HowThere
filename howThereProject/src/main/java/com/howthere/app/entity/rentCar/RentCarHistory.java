package com.howthere.app.entity.rentCar;

import com.howthere.app.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR_HISTORY")
@NoArgsConstructor
public class RentCarHistory {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private Integer carRentTotalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentCar rentCar;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public RentCarHistory(LocalDateTime startDay, LocalDateTime endDay, Integer carRentTotalPrice, RentCar rentCar, Member member) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.carRentTotalPrice = carRentTotalPrice;
        this.rentCar = rentCar;
        this.member = member;
    }
}
