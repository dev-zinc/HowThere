package com.howthere.repository.program;

import com.howthere.app.HowThereApplication;
import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.repository.program.ProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = HowThereApplication.class)
@Slf4j
@Transactional @Rollback(value = false)
public class ProgramRepositoryTests {
    @Autowired
    private ProgramRepository programRepository;

    @Test
    public void findAllWithLimitTest() {
        programRepository.findAllWithLimit(Pageable.ofSize(10), "")
                .map(ProgramDTO::toString).forEach(log::info);
    }
}
