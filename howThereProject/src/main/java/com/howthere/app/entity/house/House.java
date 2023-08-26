package com.howthere.app.entity.house;

import com.howthere.app.auditing.Period;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "TBL_HOUSE")
@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House extends Period {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String houseTitle;
    @NotNull
    private String houseContent;
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

    @OneToMany(mappedBy = "house", cascade = CascadeType.REMOVE)
    private List<Program> programs;

    @Builder
    public House(Long id, @NotNull String houseTitle, @NotNull String houseContent, Address address,
        @NotNull Integer houseMaxHeadCount, @NotNull Integer houseMaxPetCount, Member member) {
        this.id = id;
        this.houseTitle = houseTitle;
        this.houseContent = houseContent;
        this.houseAddress = address;
        this.houseMaxHeadCount = houseMaxHeadCount;
        this.houseMaxPetCount = houseMaxPetCount;
        this.member = member;
    }
}
