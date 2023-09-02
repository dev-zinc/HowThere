package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.embed.Address;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HouseService {
    Page<HouseDTO> getHouses(Pageable pageable, String keyword);

    House registerHouse(HttpServletRequest req) throws IOException;

    default House toEntity(HouseDTO houseDTO, Member member) {
        return House.builder()
            .id(houseDTO.getId())
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

    HouseDTO getHouse(Long id);

    Page<HouseDTO> getMyHouses(Pageable pageable, Long id);

    void deleteAllBy(List<Long> ids);

    void deleteHouse(Long id);
}
