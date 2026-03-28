package com.cg.repo;

import com.cg.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan, Long> {

    Optional<Loan> findByApplicantNameAndStatus(String applicantName, String status);
}