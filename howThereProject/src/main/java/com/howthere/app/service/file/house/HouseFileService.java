package com.howthere.app.service.file.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface HouseFileService {

    List<HouseFile> registerHouse(House saved, MultipartFile thumbnail, List<MultipartFile> images)
        throws IOException;

    List<HouseFile> getHouseFile(Long id);
}
