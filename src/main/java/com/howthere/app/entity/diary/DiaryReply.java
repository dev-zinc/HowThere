package com.howthere.app.entity.diary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_DIARY_REPLY")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String replyContent;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public DiaryReply(Long id, @NotNull String replyContent, Member member, Diary diary) {
        this.id = id;
        this.replyContent = replyContent;
        this.member = member;
        this.diary = diary;
    }
}
