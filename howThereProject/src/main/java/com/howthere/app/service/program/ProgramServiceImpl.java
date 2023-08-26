package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.type.Verified;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;

    @Override
    public Page<ProgramDTO> getPrograms(Pageable pageable, String keyword) {
        return programRepository.findAllWithLimit(pageable, keyword);
    }

    @Override
    @Transactional
    public void modifyAllBy(List<Long> ids) {
        programRepository.findAllById(ids).forEach(program ->
            program.setVerified(program.getVerified() == Verified.Y ? Verified.N : Verified.Y));
    }
}
