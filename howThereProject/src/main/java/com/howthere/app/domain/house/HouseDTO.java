package com.howthere.app.domain.house;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
public class HouseDTO {
    private Long id;
    private String houseAddress;
    private String houseTitle;
    private String houseContent;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    private Long memberId;

    @Builder
    public HouseDTO(String houseAddress, String houseTitle, String houseContent, LocalDateTime createdDate, Long memberId) {
        this.houseAddress = houseAddress;
        this.houseTitle = houseTitle;
        this.houseContent = houseContent;
        this.createdDate = createdDate;
        this.memberId = memberId;
    }
}
