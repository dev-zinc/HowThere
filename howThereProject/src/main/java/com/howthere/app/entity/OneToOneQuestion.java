package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_ONE_TO_ONE_QUESTION")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OneToOneQuestion extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull private String oneToOneQuestionContent;
    @Enumerated(EnumType.STRING)
    @NonNull private OneToOneQuestionType oneToOneQuestionType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
