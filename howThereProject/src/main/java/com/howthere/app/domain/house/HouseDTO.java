package com.howthere.app.domain.house;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class HouseDTO {

    private Long id;
    private String houseAddress;
    private String houseAddressDetail;
    private Double lat;
    private Double lon;
    private String houseTitle;
    private String houseContent;
    private Integer houseMaxHeadCount;
    private Integer houseMaxPetCount;
    private LocalDateTime createdDate;
    private Long memberId;

    private String thumbnail;
    @Builder
    public HouseDTO(Long id, String houseAddress, String houseAddressDetail, Double lat, Double lon,
        String houseTitle, String houseContent, Integer houseMaxHeadCount, Integer houseMaxPetCount,
        LocalDateTime createdDate, Long memberId) {
        this.id = id;
        this.houseAddress = houseAddress;
        this.houseAddressDetail = houseAddressDetail;
        this.lat = lat;
        this.lon = lon;
        this.houseTitle = houseTitle;
        this.houseContent = houseContent;
        this.houseMaxHeadCount = houseMaxHeadCount;
        this.houseMaxPetCount = houseMaxPetCount;
        this.createdDate = createdDate;
        this.memberId = memberId;
    }
    public void withThumbnail(String filePath){
        this.thumbnail = filePath;
    }
}
