package com.howthere.app.service.program;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.domain.program.ProgramReserveDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProgramReservationService {
    Page<ProgramReservationDTO> getProgramReservations(Pageable pageable, String keyword);
    void changeAllVerifiedState(List<Long> ids);

    public Long reserve(ProgramReserveDTO programReserveDTO);

    public Optional<ProgramReservation> getReservation(Long id);

    default ProgramReservation toEntity(ProgramReserveDTO programReserveDTO, Member member, Member host, Program program){
        return ProgramReservation.builder()
                .host(host)
                .member(member)
                .program(program)
                .verified(programReserveDTO.getVerified())
                .build();
    }
}
