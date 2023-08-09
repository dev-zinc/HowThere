package com.howthere.app.entity.house;

import com.howthere.app.auditing.Period;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TBL_HOUSE")
@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String houseTitle;
    @NotNull
    private String houseContent;
    @NotNull
    private Double houseLatitude;
    @NotNull
    private Double houseLongitude;
    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @ToString.Exclude
    private Member member;

    @Builder
    public House(@NotNull String houseTitle, @NotNull String houseContent, @NotNull Double houseLatitude, @NotNull Double houseLongitude, Address address, Member member) {
        this.houseTitle = houseTitle;
        this.houseContent = houseContent;
        this.houseLatitude = houseLatitude;
        this.houseLongitude = houseLongitude;
        this.address = address;
        this.member = member;
    }
}
