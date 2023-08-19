package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.repository.program.ProgramReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramReservationServiceImpl implements ProgramReservationService {
    private final ProgramReservationRepository programReservationRepository;

    @Override
    public Page<ProgramReservationDTO> getProgramReservations(Pageable pageable, String keyword) {
        return programReservationRepository.findAllWithKeyword(pageable, keyword);
    }
}
