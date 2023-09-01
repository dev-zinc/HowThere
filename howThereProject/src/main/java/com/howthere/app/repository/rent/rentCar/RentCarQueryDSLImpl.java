package com.howthere.app.repository.rent.rentCar;

import com.howthere.app.entity.file.QRentCarFile;
import com.howthere.app.entity.rent.QRentCar;
import com.howthere.app.entity.rent.QRentCarCompany;
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
import java.util.Optional;

import static com.howthere.app.entity.file.QRentCarFile.*;
import static com.howthere.app.entity.rent.QRentCar.*;
import static com.howthere.app.entity.rent.QRentCarCompany.*;
import static com.howthere.app.entity.rent.QRentCarPayment.*;

@RequiredArgsConstructor
public class RentCarQueryDSLImpl implements RentCarQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Slice<RentCar> findAllWithSlice(Pageable pageable) {

                final List<RentCar> rentCarList =
                query.selectFrom(rentCar)
                        .join(rentCar.rentCarCompany,rentCarCompany).fetchJoin()
                        .leftJoin(rentCar.rentCarFiles,rentCarFile).fetchJoin()
                .orderBy(rentCar.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() +1)
                .fetch();

                boolean hasNext = false;

            if(rentCarList.size() > pageable.getPageSize()){
                hasNext = true;
                rentCarList.remove(pageable.getPageSize());
            }

        return new SliceImpl<>(rentCarList, pageable, hasNext);
    }

    @Override
    public Optional<RentCar> findOneById_dsl(Long id) {
        return Optional.ofNullable(query.selectFrom(rentCar)
                .join(rentCar.rentCarCompany,rentCarCompany).fetchJoin()
                .leftJoin(rentCar.rentCarFiles,rentCarFile).fetchJoin()
                .where(rentCar.id.eq(id))
                .fetchOne());
    }
}
