package com.howthere.app.repository.program;

import com.howthere.app.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long>, ProgramQueryDSL {

}
