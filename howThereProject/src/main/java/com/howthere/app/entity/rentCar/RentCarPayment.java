package com.howthere.app.entity.rentCar;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.Member;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR_PAYMENT")
@SQLDelete(sql = "UPDATE TBL_RENT_CAR_PAYMENT SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentCarPayment extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private LocalDateTime startDay;
    @NotNull private LocalDateTime endDay;
    @NotNull private Integer carRentTotalPrice;
    @NotNull private boolean deleted = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    private RentCar rentCar;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public RentCarPayment(Long id, LocalDateTime startDay, LocalDateTime endDay, Integer carRentTotalPrice, boolean deleted, RentCar rentCar, Member member) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.carRentTotalPrice = carRentTotalPrice;
        this.deleted = deleted;
        this.rentCar = rentCar;
        this.member = member;
    }
}
