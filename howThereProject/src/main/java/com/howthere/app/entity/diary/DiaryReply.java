package com.howthere.app.entity.diary;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_DIARY_REPLY")
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;
}
