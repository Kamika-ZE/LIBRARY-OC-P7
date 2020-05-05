package org.mickael.librarymsloan.service.impl;


import org.mickael.librarymsloan.exception.LoanNotFoundException;
import org.mickael.librarymsloan.model.Loan;
import org.mickael.librarymsloan.model.LoanMail;
import org.mickael.librarymsloan.model.enumeration.LoanStatus;
import org.mickael.librarymsloan.repository.LoanRepository;
import org.mickael.librarymsloan.service.contract.LoanServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanServiceContract {

    private final LoanRepository loanRepository;


    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> findAll() throws LoanNotFoundException {
        List<Loan> loans = loanRepository.findAll();
        if (loans.isEmpty()){
            throw new LoanNotFoundException("No loan found");
        }
        return loans;
    }

    @Override
    public Loan findById(Integer id) throws LoanNotFoundException {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (!optionalLoan.isPresent()){
            throw new LoanNotFoundException("Loan not found in repository");
        }
        return optionalLoan.get();
    }

    @Override
    public Loan save(Loan loan) {
        Loan savedLoan = new Loan();
        savedLoan.setLoanStatus(LoanStatus.ONGOING.getLabel());
        savedLoan.setBeginLoanDate(LocalDate.now());
        savedLoan.setEndingLoanDate(LocalDate.now().plusWeeks(4));
        savedLoan.setExtendLoanDate(LocalDate.now().plusWeeks(8));
        savedLoan.setExtend(false);
        savedLoan.setBookId(loan.getBookId());
        savedLoan.setCopyId(loan.getCopyId());
        savedLoan.setCustomerId(loan.getCustomerId());
        return loanRepository.save(savedLoan);
    }

    @Override
    public Loan update(Loan loan) throws LoanNotFoundException{
        Optional<Loan> optionalLoan = loanRepository.findById(loan.getId());
        if (!optionalLoan.isPresent()){
            throw new LoanNotFoundException("Loan not found in repository");
        }
        if (loan.getLoanStatus().equalsIgnoreCase(LoanStatus.CLOSED.getLabel())){
            Loan savedLoan = optionalLoan.get();
            savedLoan.setReturnLoanDate(LocalDate.now());
            savedLoan.setLoanStatus(LoanStatus.CLOSED.getLabel());
            return loanRepository.save(savedLoan);
        }
        return loanRepository.save(loan);
    }

    @Override
    public void deleteById(Integer id) {
        loanRepository.deleteById(id);
    }

    @Override
    public List<Loan> findAllByCustomerId(Integer customerId) throws LoanNotFoundException {
        List<Loan> loans = loanRepository.findAllByCustomerId(customerId);
        if (loans.isEmpty()){
            throw new LoanNotFoundException("Loan not found in repository");
        }
        return loans;
    }

    @Override
    public List<LoanMail> findDelayLoan() {
        List<Loan> delayLoans = loanRepository.findDelayLoan();
        List<LoanMail> loanMails= new ArrayList<>();
        for (Loan loan : delayLoans){
            LoanMail loanMail = new LoanMail();
            if(loan.isExtend()){
                loanMail.setExpectedReturn(loan.getExtendLoanDate());
            } else {
                loanMail.setExpectedReturn(loan.getEndingLoanDate());
            }
            loanMail.setLoanStatus(loan.getLoanStatus());
            loanMails.add(loanMail);
        }
        return loanMails;
    }

    @Override
    public Loan extendLoan(Integer id) throws LoanNotFoundException {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (!optionalLoan.isPresent()){
            throw new LoanNotFoundException("Loan not found in repository");
        }
        Loan extendLoan = new Loan();
        extendLoan.setId(optionalLoan.get().getId());
        extendLoan.setExtend(true);
        extendLoan.setBeginLoanDate(optionalLoan.get().getBeginLoanDate());
        extendLoan.setEndingLoanDate(optionalLoan.get().getEndingLoanDate());
        extendLoan.setExtendLoanDate(optionalLoan.get().getExtendLoanDate());
        extendLoan.setLoanStatus(LoanStatus.EXTENDED.getLabel());
        return loanRepository.save(extendLoan);
    }

    @Override
    public int updateStatus(){
        List<Loan> loans = loanRepository.findAllForUpdateStatus();
        for (Loan loan : loans){
            loan.setLoanStatus(LoanStatus.OUTDATED.getLabel());
        }
        loanRepository.saveAll(loans);
        return loans.size();
    }

    @Override
    public Loan returnLoan(Integer id) throws LoanNotFoundException {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (!optionalLoan.isPresent()){
            throw new LoanNotFoundException("Loan not found in repository");
        }
        Loan savedLoan = optionalLoan.get();
        savedLoan.setLoanStatus(LoanStatus.CLOSED.getLabel());
        savedLoan.setReturnLoanDate(LocalDate.now());
        return loanRepository.save(savedLoan);
    }
}
