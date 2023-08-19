package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.house.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface HouseService {
    Page<HouseDTO> getHouses(Pageable pageable, String keyword);
    House save(HttpServletRequest req);
//    default House toEntity(HouseDTO houseDTO) {
//        return House.builder()
//                .address(houseDTO.getHouseAddress())
//                .houseTitle(houseDTO.getHouseTitle())
//                .houseContent(houseDTO.getHouseContent())
//                .houseMaxHeadCount(houseDTO.getHouseMaxHeadCount())
//                .houseMaxPetCount(houseDTO.getHouseMaxPetCount())
//                .build();
//    }
}
