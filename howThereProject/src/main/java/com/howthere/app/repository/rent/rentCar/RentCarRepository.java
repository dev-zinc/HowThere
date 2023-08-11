package com.howthere.app.repository.rent.rentCar;

import com.howthere.app.entity.rent.RentCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarRepository extends JpaRepository<RentCar,Long>,RentCarQueryDSL {
}
