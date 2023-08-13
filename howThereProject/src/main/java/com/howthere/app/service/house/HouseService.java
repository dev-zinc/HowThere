package com.howthere.app.service.house;

import com.howthere.app.domain.house.HouseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseService {
    Page<HouseDTO> getHouses(Pageable pageable, String keyword);
}
