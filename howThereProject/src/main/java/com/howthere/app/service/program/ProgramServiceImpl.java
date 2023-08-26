package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.program.Program;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.service.file.house.HouseFileService;
import com.howthere.app.service.house.HouseService;
import java.util.List;
import java.util.stream.Collectors;
import com.howthere.app.type.Verified;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    private final HouseFileService houseFileService;
    private final HouseService houseService;

    @Override
    public Page<ProgramDTO> getPrograms(Pageable pageable, String keyword) {
        return programRepository.findAllWithLimit(pageable, keyword);
    }

    @Override
    public void registerProgram(ProgramDTO dto) {
        programRepository.save(toEntity(dto));
    }

    @Override
    public Page<ProgramDTO> getProgramsWithThumbnail(Pageable pageable) {
        return programRepository.findAllWithThumbnail(pageable);
    }

    @Override
    public ProgramDTO getProgram(Long id) {
        final Program program = programRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "Not Found Program By Program Id :" + id));
        final List<HouseFile> houseFileList = houseFileService.getHouseFile(
            program.getHouse().getId());
        final List<String> filePathList = houseFileList.stream().map(file ->
            file.getFilePath()
                + "/"
                + file.getFileUuid()
        ).collect(Collectors.toList());

        return ProgramDTO.builder()
            .programAddress(program.getHouse().getHouseAddress().getAddress())
            .programAddressDetail(program.getHouse().getHouseAddress().getAddressDetail())
            .programName(program.getProgramName())
            .programContent(program.getProgramContent())
            .programPrice(program.getProgramPrice())
            .programStartDate(program.getProgramStartDate())
            .programEndDate(program.getProgramEndDate())
            .filePathList(filePathList)
            .lat(program.getHouse().getHouseAddress().getLatitude())
            .lon(program.getHouse().getHouseAddress().getLongitude())
            .build();
    @Transactional
    public void modifyAllBy(List<Long> ids) {
        programRepository.findAllById(ids).forEach(program ->
            program.setVerified(program.getVerified() == Verified.Y ? Verified.N : Verified.Y));
    }
}
