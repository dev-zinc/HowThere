package com.howthere.app.repository.rent.file;

import com.howthere.app.entity.file.RentCarFile;
import com.howthere.app.entity.rent.RentCarCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCarFileRepository extends JpaRepository<RentCarFile, Long> {
}
