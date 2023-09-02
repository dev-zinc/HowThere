package com.howthere.app.repository.file;

import com.howthere.app.domain.admin.AnnouncementDetailDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.entity.file.AnnounceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnounceFileRepository extends JpaRepository<AnnounceFile, Long> {
    AnnounceFile findByAnnouncement_Id(Long id);
    void deleteAllByAnnouncement_Id(Long id);

    default AnnounceFile toEntity(Announcement announcement, AnnouncementDetailDTO announcementDetailDTO) {
        return AnnounceFile.builder()
                .announcement(announcement)
                .fileName(announcementDetailDTO.getFileName())
                .fileSize(announcementDetailDTO.getFileSize())
                .filePath(announcementDetailDTO.getFilePath())
                .fileUuid(announcementDetailDTO.getFileUuid())
                .build();
    }
}
