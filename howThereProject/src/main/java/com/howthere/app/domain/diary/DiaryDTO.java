package com.howthere.app.domain.diary;

import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.diary.DiaryReply;
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
    private Long id;
    private Long memberId;
    private Long houseId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdDate;
    private String diaryTitle;
    private String diaryContent;
    private Integer diaryViewCount;

}
