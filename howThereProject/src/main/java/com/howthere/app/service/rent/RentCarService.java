package com.howthere.app.service.rent;


import com.howthere.app.domain.rent.RentCarDTO;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.type.RentCarType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface RentCarService {
    // 렌트카 리스트 갖고오기
    public Slice<RentCar> getRentCarList(Pageable pageable, RentCarType rentCarType);

    // 렌트카 갖고오기
    public Optional<RentCar> getRentCarById(Long id);

    public default RentCarDTO toDTO(RentCar rentCar){
            return RentCarDTO.builder()
                    .id(rentCar.getId())
                    .rentCarName(rentCar.getRentCarName())
                    .rentCarPrice(rentCar.getRentCarPrice())
                    .rentCarType(rentCar.getRentCarType())
                    .rentCarCompany(rentCar.getRentCarCompany()).build();
    }
}
