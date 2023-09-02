package com.howthere.app.repository.rent.rentCar;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.type.RentCarType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;


public interface RentCarQueryDSL {
    // 렌트카 리스트
    public Slice<RentCar> findAllWithSlice(Pageable pageable,String selectedLocal, RentCarType selectedCar);

    // 렌트카 상세정보
    public Optional<RentCar> findOneById_dsl(Long id);
}
