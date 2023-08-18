package com.howthere.app.repository.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.house.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseQueryDSL {
    Page<HouseDTO> findWithLimitAndKeyword(Pageable pageable, String keyword);
}
