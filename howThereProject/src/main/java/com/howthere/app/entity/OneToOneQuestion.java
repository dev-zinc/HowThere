package com.howthere.app.entity;

import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ONE_TO_ONE_QUESTION")
public class OneToOneQuestion {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @NonNull private OneToOneQuestionType oneToOneQuestionType;
    @NonNull private String oneToOneQuestionContent;
}
