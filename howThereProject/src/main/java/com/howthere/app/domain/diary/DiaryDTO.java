package com.howthere.app.domain.diary;

import com.howthere.app.entity.diary.DiaryLike;
import com.howthere.app.entity.diary.DiaryReply;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class DiaryDTO {
    private Long id;
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate createdDate;
    private String diaryTitle;
    private String diaryContent;
    private Integer diaryViewCount;
    private List<DiaryLike> diaryLikes = new ArrayList<>();
    private List<DiaryReply> diaryReplies = new ArrayList<>();

}
