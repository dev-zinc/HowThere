package com.howthere.app.domain.diary;

import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.diary.DiaryReply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class DiaryDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long memberId;
    private String memberName;
    private Long houseId;
    private String diaryTitle;
    private String diaryContent;
    private Integer diaryViewCount;

    @QueryProjection
    public DiaryDTO(Long id, Long memberId, String memberName, Long houseId, String diaryTitle, String diaryContent, Integer diaryViewCount) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.houseId = houseId;
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryViewCount = diaryViewCount;
    }
}
