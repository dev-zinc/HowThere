package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public Page<ProgramDTO> getPrograms(Pageable pageable, String keyword) {
        return programRepository.findAllWithLimit(pageable, keyword);
    }
}
