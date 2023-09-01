package com.howthere.app.domain.rent;

import com.howthere.app.entity.file.RentCarFile;
import com.howthere.app.entity.rent.RentCarCompany;
import com.howthere.app.type.RentCarType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ToString
@NoArgsConstructor
public class RentCarDTO {

    private Long id;
    private RentCarType rentCarType;
    private String rentCarName;
    private Integer rentCarPrice;

    private RentCarCompanyDTO rentCarCompanyDTO;
    private List<RentCarFileDTO> rentCarFileDTOS;

    @Builder
    public RentCarDTO(Long id, RentCarType rentCarType, String rentCarName, Integer rentCarPrice, RentCarCompanyDTO rentCarCompanyDTO, List<RentCarFileDTO> rentCarFileDTOS) {
        this.id = id;
        this.rentCarType = rentCarType;
        this.rentCarName = rentCarName;
        this.rentCarPrice = rentCarPrice;
        this.rentCarCompanyDTO = rentCarCompanyDTO;
        this.rentCarFileDTOS = rentCarFileDTOS;
    }
}
