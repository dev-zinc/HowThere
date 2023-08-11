package com.howthere.app.domain.rent;

import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.rent.RentCar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentCarPaymentDTO {
    private Long id;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private Integer carRentTotalPrice;
    private boolean deleted = Boolean.FALSE;

    private RentCar rentCar;

    private Member member;

}
