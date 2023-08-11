package com.howthere.app.domain.diary;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
@NoArgsConstructor
public class DiaryLikeDTO {
    private Long id;
    private Long memberId;
    private Long diaryId;

    @QueryProjection
    public DiaryLikeDTO(Long id, Long memberId, Long diaryId) {
        this.id = id;
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}
