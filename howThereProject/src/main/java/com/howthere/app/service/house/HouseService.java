package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseService {

    Page<HouseDTO> getHouses(Pageable pageable, String keyword);

    House save(HttpServletRequest req);

    default House toEntity(HouseDTO houseDTO, Member member) {
        return House.builder()
            .address(Address.builder()
                .address(houseDTO.getHouseAddress())
                .addressDetail(houseDTO.getHouseAddressDetail())
                .latitude(houseDTO.getLat())
                .longitude(houseDTO.getLon())
                .build())
            .houseTitle(houseDTO.getHouseTitle())
            .houseContent(houseDTO.getHouseContent())
            .houseMaxHeadCount(houseDTO.getHouseMaxHeadCount())
            .houseMaxPetCount(houseDTO.getHouseMaxPetCount())
            .member(member)
            .build();
    }
}
