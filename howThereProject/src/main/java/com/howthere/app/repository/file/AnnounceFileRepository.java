package com.howthere.app.repository.file;

import com.howthere.app.entity.file.AnnounceFile;
import com.howthere.app.entity.file.DiaryFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.File;

public interface AnnounceFileRepository extends FileRepository<AnnounceFile> {
}
