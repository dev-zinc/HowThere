package com.howthere.app.domain.program;

import com.howthere.app.type.Verified;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProgramDTO {

    private Long id;
    private Long memberId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime endDate;
    private String programAddress;
    private String programAddressDetail;
    private String programName;
    private String programContent;
    private Integer programPrice;
    private LocalDate programStartDate;
    private LocalDate programEndDate;
    private Long houseId;
    private Verified verified;
    private String thumbnail;
    private List<String> filePathList;
    private Double lat;
    private Double lon;
    private Integer houseMaxHeadCount;
    private Integer houseMaxPetCount;
    private String hostName;
    private String hostEmail;
    private String hostProfile;
    private long between;

    @Builder
    public ProgramDTO(Long id, Long memberId, LocalDateTime createdDate, LocalDateTime endDate,
        String programAddress, String programAddressDetail, String programName,
        String programContent,
        Integer programPrice, LocalDate programStartDate, LocalDate programEndDate, Long houseId,
        Verified verified, String thumbnail, List<String> filePathList, Double lat, Double lon,
        Integer houseMaxHeadCount, Integer houseMaxPetCount, String hostName, String hostEmail,
        String hostProfile, long between) {
        this.id = id;
        this.memberId = memberId;
        this.createdDate = createdDate;
        this.endDate = endDate;
        this.programAddress = programAddress;
        this.programAddressDetail = programAddressDetail;
        this.programName = programName;
        this.programContent = programContent;
        this.programPrice = programPrice;
        this.programStartDate = programStartDate;
        this.programEndDate = programEndDate;
        this.houseId = houseId;
        this.verified = verified;
        this.thumbnail = thumbnail;
        this.filePathList = filePathList;
        this.lat = lat;
        this.lon = lon;
        this.houseMaxHeadCount = houseMaxHeadCount;
        this.houseMaxPetCount = houseMaxPetCount;
        this.hostName = hostName;
        this.hostEmail = hostEmail;
        this.hostProfile = hostProfile;
        this.between = between;
    }
}
