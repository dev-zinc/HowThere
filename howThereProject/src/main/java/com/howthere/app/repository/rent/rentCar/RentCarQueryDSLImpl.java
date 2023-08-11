package com.howthere.app.repository.rent.rentCar;

import com.howthere.app.entity.rent.QRentCar;
import com.howthere.app.entity.rent.QRentCarPayment;
import com.howthere.app.entity.rent.RentCar;
import com.howthere.app.type.RentCarType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;


import java.util.List;

import static com.howthere.app.entity.rent.QRentCar.*;
import static com.howthere.app.entity.rent.QRentCarPayment.*;

@RequiredArgsConstructor
public class RentCarQueryDSLImpl implements RentCarQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Slice<RentCar> findAllWithSlice(Pageable pageable, RentCarType rentCarType) {
                if (rentCarType == null){
                    rentCarType = RentCarType.CompactCar;
                }

                final List<RentCar> rentCarList =
                        query.select(rentCar).from(rentCar)
                .orderBy(rentCar.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() +1)
                .where(rentCar.rentCarType.eq(rentCarType))
                .fetch();

                boolean hasNext = false;

            if(rentCarList.size() > pageable.getPageSize()){
                hasNext = true;
                rentCarList.remove(pageable.getPageSize());
            }

        return new SliceImpl<>(rentCarList, pageable, hasNext);
    }
}
