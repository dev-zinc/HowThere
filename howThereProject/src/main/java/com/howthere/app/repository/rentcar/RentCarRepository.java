package com.howthere.app.repository.rentcar;

import com.howthere.app.entity.RentCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarRepository extends JpaRepository<RentCar,Long> {
}
