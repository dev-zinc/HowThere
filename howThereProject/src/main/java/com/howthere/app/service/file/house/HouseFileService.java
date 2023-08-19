package com.howthere.app.service.file.house;

import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseFileService {
    List<HouseFile> registerHouse(House saved, MultipartFile thumbnail, List<MultipartFile> images)
        throws IOException;

    void setHouseThumbnailList(List<HouseDTO> houseDTOList);
}
