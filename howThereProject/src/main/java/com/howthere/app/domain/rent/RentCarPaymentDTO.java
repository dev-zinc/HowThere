package com.howthere.app.domain.rent;

import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.rent.RentCar;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Component
@ToString
@NoArgsConstructor
public class RentCarPaymentDTO {
    private Long id;
    private LocalDate startDay;
    private LocalDate endDay;
    private Integer carRentTotalPrice;
    private boolean deleted = Boolean.FALSE;

    private RentCar rentCar;

    private Member member;

    @Builder
    public RentCarPaymentDTO(Long id, LocalDate startDay, LocalDate endDay, Integer carRentTotalPrice, boolean deleted, RentCar rentCar, Member member) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.carRentTotalPrice = carRentTotalPrice;
        this.deleted = deleted;
        this.rentCar = rentCar;
        this.member = member;
    }
}
