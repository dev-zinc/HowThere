package com.howthere.app.service.file.house.impl;

import com.howthere.app.config.ConstantPool;
import com.howthere.app.domain.house.HouseDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import com.howthere.app.repository.file.house.HouseFileRepository;
import com.howthere.app.service.file.house.HouseFileService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class HouseFileServiceImpl implements HouseFileService {

    private static final String BASE_PATH = ConstantPool.getFileRootPath();
    private final HouseFileRepository houseFileRepository;

    private static Image getImage(MultipartFile multipartFile, House house) throws IOException {
        final String originalFilename = multipartFile.getOriginalFilename();
        final int lastIndexOf = originalFilename.lastIndexOf(".");
        final String extension = originalFilename.substring(lastIndexOf);
        final String uuid = UUID.randomUUID() + extension;
        final long fileSize = multipartFile.getSize();

        final byte[] bytes = multipartFile.getBytes();
        String temp = "";
        try {
            final Path path = Paths.get(BASE_PATH)
                .resolve(String.valueOf(LocalDateTime.now().getYear()))
                .resolve(String.valueOf(house.getId()));
            final Path file = path.resolve(uuid);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            if (Files.notExists(file)) {
                Files.createFile(file);
                Files.write(file, bytes, StandardOpenOption.WRITE);
                temp = file.toString();
            }
            return new Image(originalFilename, uuid, fileSize, path);
        } catch (IOException e) {
            log.error("Create File Error", e);
            Files.deleteIfExists(Paths.get(temp));
            throw e;
        }
    }

    @Override
    public List<HouseFile> registerHouse(House saved, MultipartFile thumbnail,
        List<MultipartFile> images) throws IOException {
        try {
            final List<HouseFile> houseFileList = new ArrayList<>();
            final Image thumbnailImage = getImage(thumbnail, saved);

            final HouseFile thumbnailFile = HouseFile.builder()
                .filePath(thumbnailImage.path)
                .fileUuid(thumbnailImage.uuid)
                .fileName(thumbnailImage.originalFilename)
                .fileSize(thumbnailImage.fileSize)
                .house(saved)
                .thumb(true)
                .build();
            houseFileList.add(thumbnailFile);
            for (MultipartFile v : images) {
                final Image image = getImage(v, saved);
                houseFileList.add(HouseFile.builder()
                    .filePath(image.path)
                    .fileName(image.originalFilename)
                    .fileUuid(image.uuid)
                    .fileSize(image.fileSize)
                    .house(saved)
                    .build());
            }
            return houseFileRepository.saveAll(houseFileList);
        } catch (Exception e) {
            log.error("House File Save Error", e);
            throw e;
        }
    }

    @Override
    public void setHouseThumbnailList(List<HouseDTO> houseDTOList) {
        final List<Long> houseIdList = houseDTOList.stream()
            .map(HouseDTO::getId)
            .collect(Collectors.toList());

        final List<HouseFile> fileList = houseFileRepository.findByHouseIdInAndThumbOrderByHouseIdDesc(
            houseIdList, true);
        for (int i = 0; i < houseDTOList.size(); i++) {
            final HouseDTO houseDTO = houseDTOList.get(i);
            final HouseFile houseFile = fileList.get(i);

            houseDTO.setThumbnail(houseFile.getFilePath() + "/" + houseFile.getFileUuid());
        }
    }

    private static class Image {

        public final String originalFilename;
        public final String uuid;
        public final long fileSize;
        private final String path;

        public Image(String originalFilename, String uuid, long fileSize, Path path) {
            this.originalFilename = originalFilename;
            this.uuid = uuid;
            this.fileSize = fileSize;
            this.path = path.toString();
        }
    }
}
