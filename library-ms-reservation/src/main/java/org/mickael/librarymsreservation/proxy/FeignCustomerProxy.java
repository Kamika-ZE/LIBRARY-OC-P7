package org.mickael.librarymsreservation.proxy;


import org.mickael.librarymsreservation.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "library-ms-customer", url = "localhost:8000")
public interface FeignCustomerProxy {


    @GetMapping("/api/customers/{id}")
    Customer retrieveCustomer(@PathVariable("id") Integer id);
}
