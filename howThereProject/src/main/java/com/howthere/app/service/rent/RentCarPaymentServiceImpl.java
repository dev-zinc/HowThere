package com.howthere.app.service.rent;

import com.howthere.app.entity.rent.RentCarPayment;
import com.howthere.app.repository.rent.payment.RentCarPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentCarPaymentServiceImpl implements RentCarPaymentService {
    private final RentCarPaymentRepository rentCarPaymentRepository;

    @Override
    public void reserveRentCar(RentCarPayment rentCarPayment) {
        rentCarPaymentRepository.save(rentCarPayment);
    }

    @Override
    public List<RentCarPayment> getRentCarPaymentListByMemberId(Long memberId) {
        return rentCarPaymentRepository.findAllByMemberId_queryDSL(memberId);
    }

    @Override
    public Optional<RentCarPayment> getRentCarPaymentById(Long id) {
        return rentCarPaymentRepository.findById(id);
    }
}
