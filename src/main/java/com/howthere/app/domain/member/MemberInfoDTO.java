package com.howthere.app.domain.member;

import com.howthere.app.type.LoginType;
import com.howthere.app.type.MemberType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Getter @ToString
@NoArgsConstructor
public class MemberInfoDTO {
    private Long id;
    private String memberEmail;
    private String memberName;
    private LocalDate memberBirthDate;
    private LocalDateTime createdDate;
    private boolean activated;

    @Builder
    public MemberInfoDTO(String memberEmail, String memberName, LocalDate memberBirthDate, LocalDateTime createdDate, boolean activated) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberBirthDate = memberBirthDate;
        this.createdDate = createdDate;
        this.activated = activated;
    }
}
