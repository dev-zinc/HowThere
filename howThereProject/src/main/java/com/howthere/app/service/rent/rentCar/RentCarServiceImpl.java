package com.howthere.app.service.rent.rentCar;

import com.howthere.app.domain.rent.RentCarDTO;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.repository.rent.rentCar.RentCarRepository;
import com.howthere.app.service.rent.rentCar.RentCarService;
import com.howthere.app.type.RentCarType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RentCarServiceImpl implements RentCarService {
    private final RentCarRepository rentCarRepository;

    // 렌트카 리스트 갖고오기
    @Override
    public Slice<RentCarDTO> getRentCarList(Pageable pageable) {
        Slice<RentCar> rentCars = rentCarRepository.findAllWithSlice(pageable);

        List<RentCarDTO> rentCarDTOs = rentCars.stream()
                .map(this::RentCarToDTO)
                .collect(Collectors.toList());
        log.info("#######{}"+rentCarDTOs.size());
        rentCars.forEach(rentCar -> log.info(rentCar.toString()));

        return  new SliceImpl<>(rentCarDTOs, pageable, rentCars.hasNext());
    }

    // 렌트카 상세정보 갖고오기
    @Override
    public RentCarDTO getRentCarById(Long id) {
        return RentCarToDTO(rentCarRepository.findOneById_dsl(id).get());
    }


}
