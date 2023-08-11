package com.howthere.app.service.rent;

import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.type.RentCarType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RentCarServiceImpl implements RentCarService {
    private final RentCarRepository rentCarRepository;

    // 렌트카 리스트 갖고오기
    @Override
    public Slice<RentCar> getRentCarList(Pageable pageable, RentCarType rentCarType) {
        Slice<RentCar> rentCars = rentCarRepository.findAllWithSlice(pageable,rentCarType);

        return rentCars;
    }

    @Override
    public Optional<RentCar> getRentCarById(Long id) {
        return rentCarRepository.findById(id);
    }
}
