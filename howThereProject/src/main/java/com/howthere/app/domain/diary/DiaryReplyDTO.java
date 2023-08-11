package com.howthere.app.domain.diary;

import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class DiaryReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private Long memberId;
    private String memberName;
    private Long diaryId;

    @QueryProjection
    public DiaryReplyDTO(Long id, String replyContent, Long memberId, String memberName, Long diaryId) {
        this.id = id;
        this.replyContent = replyContent;
        this.memberId = memberId;
        this.memberName = memberName;
        this.diaryId = diaryId;
    }
}
