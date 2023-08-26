package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.program.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramService {

    Page<ProgramDTO> getPrograms(Pageable pageable, String keyword);

    void registerProgram(ProgramDTO dto);

    default Program toEntity(ProgramDTO dto) {
        return Program.builder()
            .programStartDate(dto.getProgramStartDate())
            .programEndDate(dto.getProgramEndDate())
//            .programName(dto.getProgramName())
            .programName("프로그램 네임") // TODO: 2023/08/25 프로그램 이름 설정 어떻게 해야하는지..
            .programContent(dto.getProgramContent())
            .programPrice(dto.getProgramPrice())
            .house(House.builder().id(dto.getHouseId()).build())
            .build();
    }

    Page<ProgramDTO> getProgramsWithThumbnail(Pageable pageable);

    ProgramDTO getProgram(Long id);
}
