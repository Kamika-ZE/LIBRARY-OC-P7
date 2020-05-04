package org.mickael.libraryclientweb.controller;

import org.mickael.libraryclientweb.proxy.FeignBookProxy;
import org.mickael.libraryclientweb.proxy.FeignCustomerProxy;
import org.mickael.libraryclientweb.proxy.FeignLoanProxy;
import org.mickael.libraryclientweb.security.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoanController {


    private final FeignBookProxy feignBookProxy;
    private final FeignLoanProxy feignLoanProxy;
    private final FeignCustomerProxy feignCustomerProxy;
    private static final String REDIRECT_USER_HOME_VIEW = "redirect:/accounts/dashboard";


    @Autowired
    public LoanController(FeignBookProxy feignBookProxy, FeignLoanProxy feignLoanProxy, FeignCustomerProxy feignCustomerProxy) {
        this.feignBookProxy = feignBookProxy;
        this.feignLoanProxy = feignLoanProxy;
        this.feignCustomerProxy = feignCustomerProxy;
    }


    @GetMapping("/extend/{loanId}")
    public String extendLoan(/*@CookieValue(value = CookieUtils.HEADER, required = false)String accessToken,*/ @PathVariable Integer loanId){
        feignLoanProxy.extendLoan(loanId/*,"Bearer " + accessToken*/);
        return REDIRECT_USER_HOME_VIEW;
    }

}
