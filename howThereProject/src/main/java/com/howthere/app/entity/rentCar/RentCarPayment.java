package com.howthere.app.entity.rentCar;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.Member;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR_PAYMENT")
@SQLDelete(sql = "UPDATE TBL_RENT_CAR_PAYMENT SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
@NoArgsConstructor
public class RentCarPayment extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String paymentContent;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.REMOVE)
    private RentCarHistory rentCarHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public RentCarPayment(String paymentContent, RentCarHistory rentCarHistory, Member member) {
        this.paymentContent = paymentContent;
        this.rentCarHistory = rentCarHistory;
        this.member = member;
    }
}
