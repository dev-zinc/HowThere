package com.howthere.app.entity.house;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TBL_HOUSE")
@Entity
@ToString
@Getter
public class House extends Period {
    @Id
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
    @NotNull
    private String houseAddress;
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
                 String houseAddress, Integer houseMaxHeadCount, Integer houseMaxPetCount, Member member) {
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
