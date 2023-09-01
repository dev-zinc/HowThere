package com.howthere.app.service.program;

import static com.howthere.app.type.Verified.N;
import static com.howthere.app.type.Verified.Y;

import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.domain.program.ProgramReserveDTO;
import com.howthere.app.entity.file.HouseFile;
import com.howthere.app.entity.house.House;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramReservation;
import com.howthere.app.repository.file.house.HouseFileRepository;
import com.howthere.app.repository.house.HouseRepository;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.program.ProgramRepository;
import com.howthere.app.repository.program.ProgramReservationRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgramReservationServiceImpl implements ProgramReservationService {

    private final ProgramReservationRepository programReservationRepository;
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;
    private final HouseRepository houseRepository;
    private final HouseFileRepository fileRepository;

    @Override
    public Page<ProgramReservationDTO> getProgramReservations(Pageable pageable, String keyword) {
        return programReservationRepository.findAllWithKeyword(pageable, keyword);
    }

    @Override
    @Transactional
    public void changeAllVerifiedState(List<Long> ids) {
        programReservationRepository.findAllById(ids).forEach(reservation ->
            reservation.setVerified(reservation.getVerified() == Y ? N : Y));
    }

    @Override
    public Long reserve(ProgramReserveDTO programReserveDTO) {
        Member member = memberRepository.findById(programReserveDTO.getMemberId()).orElseThrow();
        House house = houseRepository.findById(programReserveDTO.getHouseId()).orElseThrow();
        Member host = house.getMember();
        Program program = programRepository.findById(programReserveDTO.getProgramId())
            .orElseThrow();
        ProgramReservation programReservation = programReservationRepository.save(
            toEntity(programReserveDTO, member, host, program));
        program.setVerified(N);
        programRepository.save(program);
        return programReservation.getId();
    }

    @Override
    public Optional<ProgramReservation> getReservation(Long id) {
        return programReservationRepository.findById(id);
    }

    @Override
    public Page<ProgramReservationDTO> getReservationByMemberId(Pageable pageable, Long id) {
        final Page<ProgramReservationDTO> reservation = programReservationRepository.getReservationByMemberId(
            pageable, id);
        final List<ProgramReservationDTO> content = reservation.getContent();
        content.forEach(v -> {
            final List<HouseFile> fileList = fileRepository.findByHouseIdAndThumb(v.getHouseId(),
                true);
            v.setFilePath(fileList.get(0).getFilePath() + "/" + fileList.get(0).getFileUuid());
            final LocalDate startDate = v.getProgramStartDate();
            final LocalDate endDate = v.getProgramEndDate();

            final long between = ChronoUnit.DAYS.between(startDate, endDate);
            v.setProgramPrice((int) (v.getProgramPrice() * between));
        });
        return reservation;
    }

    @Override
    public void deleteReservation(ProgramReservationDTO reservationDTO) {
        final ProgramReservation programReservation = programReservationRepository.findById(
            reservationDTO.getId()).orElseThrow(RuntimeException::new);
        programReservationRepository.delete(programReservation);
    }
}
