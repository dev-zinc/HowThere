package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_RENT_CAR_PAYMENT")
@SQLDelete(sql = "UPDATE TBL_RENT_CAR_PAYMENT SET DELETED = 1 WHERE ID = ?")
@Where(clause = "DELETED = 0")
public class RentCarPayment extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String paymentContent;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.REMOVE)
    private RentCarHistory rentCarHistory;

    //@ManyToOne
    //private Member member;

}
