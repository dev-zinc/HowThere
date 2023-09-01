package com.howthere.app.service.program;

import com.howthere.app.domain.program.ProgramPaymentDTO;
import com.howthere.app.domain.program.ProgramReservationDTO;
import com.howthere.app.entity.member.Member;
import com.howthere.app.entity.program.Program;
import com.howthere.app.entity.program.ProgramPayment;
import com.howthere.app.repository.member.MemberRepository;
import com.howthere.app.repository.program.ProgramPaymentRepository;
import com.howthere.app.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramPaymentServiceImpl implements ProgramPaymentService {
    private final ProgramPaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;

    @Override
    public ProgramPayment save(ProgramReservationDTO reservationDTO) {
        final Member member = memberRepository.findById(reservationDTO.getMemberId())
            .orElseThrow(RuntimeException::new);
        final Program program = programRepository.findById(reservationDTO.getProgramId())
            .orElseThrow(RuntimeException::new);

        final ProgramPayment payment = ProgramPayment.builder()
            .paymentPrice(reservationDTO.getProgramPrice())
            .member(member)
            .program(program)
            .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Page<ProgramPaymentDTO> getPaymentListByMemberId(Pageable pageable, Long id) {
        return paymentRepository.getPaymentListByMemberId(pageable, id);
    }
}
