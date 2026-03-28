package com.cg.service;

import com.cg.entity.Loan;
import com.cg.exception.*;
import com.cg.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepo loanRepository;

    @Override
    public Loan createLoan(Loan loan) {

        // Rule 1: Amount validation
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

        // Rule 2: Duplicate pending loan
        loanRepository.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING")
                .ifPresent(l -> {
                    throw new DuplicateLoanApplicationException("User already has a pending loan");
                });

        loan.setStatus("PENDING");

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));
    }

    @Override
    public Loan updateLoanStatus(Long id, String status) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));

        loan.setStatus(status);

        return loanRepository.save(loan);
    }
}