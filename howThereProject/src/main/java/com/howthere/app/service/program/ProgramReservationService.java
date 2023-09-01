package com.howthere.app.service.program;

import com.howthere.app.domain.diary.DiaryDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.entity.diary.Diary;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramReservationService {
    Page<ProgramReservationDTO> getProgramReservations(Pageable pageable, String keyword);
    void changeAllVerifiedState(List<Long> ids);

    public Long reserve(ProgramReservationDTO programReservationDTO, Long programId);

    default ProgramReservation toEntity(ProgramReservationDTO programReservationDTO, Member member, Member host, Program program){
        return ProgramReservation.builder()
                .host(host)
                .member(member)
                .program(program)
                .verified(programReservationDTO.getVerified())
                .build();
    }
}
