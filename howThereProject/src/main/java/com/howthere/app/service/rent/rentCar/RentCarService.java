package com.howthere.app.service.rent.rentCar;


import com.howthere.app.domain.rent.RentCarCompanyDTO;
import com.howthere.app.domain.rent.RentCarDTO;
import com.howthere.app.domain.rent.RentCarFileDTO;
import com.howthere.app.entity.file.RentCarFile;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.type.RentCarType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface RentCarService {
    // 렌트카 리스트 갖고오기
    public Slice<RentCarDTO> getRentCarList(Pageable pageable, RentCarType rentCarType);

    // 렌트카 상세 정보 갖고오기
    public RentCarDTO getRentCarById(Long id);


    default RentCarDTO RentCarToDTO(RentCar rentCar){
        List<RentCarFileDTO> rentCarFileDTOS = new ArrayList<>();

        for (RentCarFile rentCarFile : rentCar.getRentCarFiles()) {
            RentCarFileDTO rentCarFileDTO = RentCarFileDTO.builder()
                    .id(rentCarFile.getId())
                    .fileName(rentCarFile.getFileName())
                    .fileUuid(rentCarFile.getFileUuid())
                    .filePath(rentCarFile.getFilePath())
                    .fileSize(rentCarFile.getFileSize())
                    .build();

            rentCarFileDTOS.add(rentCarFileDTO);
        }

        RentCarCompanyDTO rentCarCompanyDTO = RentCarCompanyDTO.builder()
                .id(rentCar.getRentCarCompany().getId())
                .rentCarCompanyName(rentCar.getRentCarCompany().getRentCarCompanyName())
                .rentCarCompanyAddress(rentCar.getRentCarCompany().getRentCarCompanyAddress())
                .build();

        return RentCarDTO.builder()
                .id(rentCar.getId())
                .rentCarName(rentCar.getRentCarName())
                .rentCarPrice(rentCar.getRentCarPrice())
                .rentCarType(rentCar.getRentCarType())
                .rentCarFileDTOS(rentCarFileDTOS)
                .rentCarCompanyDTO(rentCarCompanyDTO)
                .build();
    }
}
