package com.howthere.app.repository.file.house;

import com.howthere.app.entity.file.HouseFile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseFileRepository extends JpaRepository<HouseFile, Long> {

    List<HouseFile> findByHouseIdInAndThumb(List<Long> houseIdList, boolean thumb);
}
