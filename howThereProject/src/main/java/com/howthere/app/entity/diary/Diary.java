package com.howthere.app.entity.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howthere.app.auditing.Period;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_DIARY")
@Getter @ToString(callSuper = true)
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String diaryTitle;
    @NotNull private String diaryContent;
    @ColumnDefault(value = "0")
    private Integer diaryViewCount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private House house;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    private List<DiaryLike> diaryLikes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary")
    private List<DiaryReply> diaryReplies = new ArrayList<>();

    @Builder
    public Diary(Long id, String diaryTitle, String diaryContent, Member member, House house) {
        this.id = id;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.member = member;
        this.house = house;
    }
}
