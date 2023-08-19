package com.howthere.app.repository.program;

import com.howthere.app.entity.program.ProgramReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramReservationRepository extends JpaRepository<ProgramReservation, Long>, ProgramReservationQueryDSL {

}
