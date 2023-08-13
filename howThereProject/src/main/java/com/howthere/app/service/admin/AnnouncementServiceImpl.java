package com.howthere.app.service.admin;

import com.howthere.app.domain.admin.AnnouncementDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementDTO getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(RuntimeException::new);
        return toDTO(announcement);
    }

    @Override
    public void save(AnnouncementDTO announcementDTO) {
        announcementRepository.save(toEntity(announcementDTO));
    }

    @Override
    public void remove(AnnouncementDTO announcementDTO) {
        announcementRepository.delete(toEntity(announcementDTO));
    }

    @Override
    public Page<AnnouncementDTO> getAnnouncementList(Pageable pageable, String keyword) {

        return announcementRepository.findAllQueryDSL(pageable, keyword);
    }
}
