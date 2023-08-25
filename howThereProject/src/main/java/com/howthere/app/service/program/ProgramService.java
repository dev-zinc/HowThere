package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.program.Program;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService {

    Page<ProgramDTO> getPrograms(Pageable pageable, String keyword);

    void registerProgram(ProgramDTO dto);

    default Program toEntity(ProgramDTO dto) {
        return Program.builder()
            .programStartDate(LocalDateTime.from(dto.getProgramStartDate()))
            .programEndDate(LocalDateTime.from(dto.getProgramEndDate()))
            .programName(dto.getProgramName())
            .programContent(dto.getProgramContent())
            .programPrice(dto.getProgramPrice())
            .house(House.builder().id(dto.getHouseId()).build())
            .build();
    }
}
