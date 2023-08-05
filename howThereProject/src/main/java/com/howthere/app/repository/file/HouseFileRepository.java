package com.howthere.app.repository.file;

import com.howthere.app.entity.file.HouseFile;

import java.util.Optional;

public interface HouseFileRepository extends FileRepository<HouseFile> {
    Optional<HouseFile> findByIdAndThumbEquals(Long id, boolean thumb);
}
