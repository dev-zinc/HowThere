package com.howthere.app.entity.house;

import com.howthere.app.auditing.Period;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "TBL_HOUSE")
@ToString @Getter
@NoArgsConstructor
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
    private Address houseAddress;
    @NotNull
    private Integer houseMaxHeadCount;
    @NotNull
    private Integer houseMaxPetCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    @ToString.Exclude
    private Member member;

    @Builder
    public House(String houseTitle, String houseContent, Double houseLatitude, Double houseLongitude,
                 Address houseAddress, Integer houseMaxHeadCount, Integer houseMaxPetCount, Member member) {
        this.houseTitle = houseTitle;
        this.houseContent = houseContent;
        this.houseLatitude = houseLatitude;
        this.houseLongitude = houseLongitude;
        this.houseAddress = houseAddress;
        this.houseMaxHeadCount = houseMaxHeadCount;
        this.houseMaxPetCount = houseMaxPetCount;
        this.member = member;
    }
}
