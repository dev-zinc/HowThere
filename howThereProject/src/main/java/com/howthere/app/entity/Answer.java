package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_ANSWER")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    private OneToOneQuestion oneToOneQuestion;
}
