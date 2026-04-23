package com.cg.controller;

import com.cg.entity.Loan;
import com.cg.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
  Loggerlogger =LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoanService loanService;

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PutMapping("/{id}/status")
    public Loan updateLoanStatus(@PathVariable Long id,
                                 @RequestBody Loan request) {
        return loanService.updateLoanStatus(id, request.getStatus());
    }
}
