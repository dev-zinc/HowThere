package com.howthere.app.entity.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DIARY_LIKE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryLike {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public DiaryLike(Long id, Member member, Diary diary) {
        this.id = id;
        this.member = member;
        this.diary = diary;
    }
}
