package com.howthere.app.repository;

import com.howthere.app.entity.file.DiaryFile;
import com.howthere.app.entity.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
