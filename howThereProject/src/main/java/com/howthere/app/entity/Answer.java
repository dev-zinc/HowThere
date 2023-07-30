package com.howthere.app.entity;

import com.howthere.app.auditing.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TBL_ANSWER")
@Getter
@Setter
@ToString
@Entity
public class Answer extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String answerContent;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private OneToOneQuestion oneToOneQuestion;
}
