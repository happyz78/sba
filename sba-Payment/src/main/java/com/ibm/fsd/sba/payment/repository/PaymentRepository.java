package com.ibm.fsd.sba.payment.repository;

import com.ibm.fsd.sba.payment.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {
    Payments findByTrainingId(Long id);
}
