package com.howthere.app.domain.diary;

import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime updatedDate;

    @QueryProjection
    public DiaryReplyDTO(Long id, String replyContent, Long memberId, String memberName, Long diaryId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.replyContent = replyContent;
        this.memberId = memberId;
        this.memberName = memberName;
        this.diaryId = diaryId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
