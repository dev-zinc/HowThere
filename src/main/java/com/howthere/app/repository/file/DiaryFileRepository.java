package com.howthere.app.repository.file;

import com.howthere.app.entity.file.DiaryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryFileRepository extends JpaRepository<DiaryFile, Long> {
}
