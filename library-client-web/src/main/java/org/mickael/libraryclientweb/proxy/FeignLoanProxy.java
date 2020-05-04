package org.mickael.libraryclientweb.proxy;

import org.mickael.libraryclientweb.bean.loan.LoanBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "library-ms-loan", url = "localhost:8200")//il faut modifier les uri avec le nom du ms à appeler
//@RibbonClient(name = "micro service à appeler")
public interface FeignLoanProxy {


    /* ==== LOAN ==== */

    @GetMapping("/api/loans")
    List<LoanBean> getLoans();

    @GetMapping("/api/loans/{id}")
    LoanBean retrieveLoan(@PathVariable("id") Integer id);

    @PostMapping("/api/loans/new-loan")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<LoanBean> createNewLoan(@Valid @RequestBody LoanBean newLoanBean);

    @PutMapping("/api/loans/{id}")
    @ResponseStatus(HttpStatus.OK)
    LoanBean updateLoan(@PathVariable("id") Integer id, @RequestBody LoanBean loanBean);

    @DeleteMapping("/api/loans/{id}")
    void deleteLoan(@PathVariable("id") Integer id);

    @GetMapping("/api/loans/customer/{customerId}")
    List<LoanBean> findAllByCustomerId(@PathVariable("customerId") Integer customerId/*, @RequestHeader("Authorization") String accessToken*/);

    @GetMapping("/api/loans/extend/{id}")
    LoanBean extendLoan(@PathVariable("id") Integer id/*, @RequestHeader("Authorization") String accessToken*/);

}
