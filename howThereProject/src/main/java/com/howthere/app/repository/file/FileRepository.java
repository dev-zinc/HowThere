package com.howthere.app.repository.file;

import com.howthere.app.entity.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository<E extends FileEntity> extends JpaRepository<E, Long> {

}
