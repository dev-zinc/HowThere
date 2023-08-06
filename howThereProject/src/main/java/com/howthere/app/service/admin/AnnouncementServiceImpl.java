package com.howthere.app.service.admin;

import com.howthere.app.domain.AnnouncementDTO;
import com.howthere.app.entity.admin.Announcement;
import com.howthere.app.repository.admin.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementDTO getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow(() -> new RuntimeException());
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
    public Page<AnnouncementDTO> getAnnouncementList(Pageable pageable) {
        Page<Announcement> announcements = announcementRepository.findAll(pageable);
        List<AnnouncementDTO> dtoList = new ArrayList<>();

        for(Announcement announcement : announcements.getContent()){
            dtoList.add(toDTO(announcement));
        }

        return new PageImpl<>(dtoList, pageable, announcementRepository.count());
    }
}
