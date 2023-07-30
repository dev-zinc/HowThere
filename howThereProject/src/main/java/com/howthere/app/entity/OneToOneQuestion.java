package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ONE_TO_ONE_QUESTION")
public class OneToOneQuestion extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @NonNull private OneToOneQuestionType oneToOneQuestionType;
    @NonNull private String oneToOneQuestionContent;
}
