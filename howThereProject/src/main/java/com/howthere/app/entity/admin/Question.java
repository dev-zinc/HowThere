package com.howthere.app.entity.admin;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import com.howthere.app.type.QuestionType;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ONE_TO_ONE_QUESTION")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull private String oneToOneQuestionContent;
    @Enumerated(EnumType.STRING)
    @NonNull private QuestionType oneToOneQuestionType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
