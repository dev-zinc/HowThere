package com.howthere.app.repository.rent.rentCar;

import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.type.RentCarType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface RentCarQueryDSL {
    public Slice<RentCar> findAllWithSlice(Pageable pageable, RentCarType rentCarType);

}
