package org.mickael.libraryclientweb.proxy;

import org.mickael.libraryclientweb.bean.customer.AccountLoginBean;
import org.mickael.libraryclientweb.bean.customer.AddressBean;
import org.mickael.libraryclientweb.bean.customer.CustomerBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "library-ms-customer", url = "localhost:8000")//il faut modifier les uri avec le nom du ms à appeler
//@RibbonClient(name = "micro service à appeler")
public interface FeignCustomerProxy {


    /* ================================ */
    /* === No Authentication needed === */
    /* ================================ */


    /* ==== CUSTOMERS ==== */
    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody AccountLoginBean accountLoginBean);

    @PostMapping(value = "/register")
    ResponseEntity<?> register(@RequestBody CustomerBean customerBean);

    /* =============================== */
    /* ==== Authentication needed ==== */
    /* =============================== */

    /* ==== CUSTOMERS ==== */
    @GetMapping("/api/customers")
    List<CustomerBean> getAccounts();

    @GetMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerBean retrieveAccount(@PathVariable("id") Integer id/*, @RequestHeader("Authorization") String accessToken*/);

    @PostMapping("/api/customers/new-customer")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<CustomerBean> createNewAccount(@Valid @RequestBody CustomerBean newCustomerBean);

    @PutMapping("/api/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerBean updateAccount(@PathVariable("id") Integer id, @RequestBody CustomerBean customerBean/*, @RequestHeader("Authorization") String accessToken*/);

    @DeleteMapping("/api/customers/{id}")
    ResponseEntity<Void> deleteAccount(@PathVariable("id") Integer id);



    /* ==== ADDRESSES ==== */
    @GetMapping("/api/addresses")
    List<AddressBean> getAddresses();

    @GetMapping("/api/addresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressBean retrieveAddress(@PathVariable("id") Integer id);

    @PostMapping("/api/addresses/new-address")
    @ResponseStatus(HttpStatus.CREATED)
    AddressBean createNewAddress(@RequestBody AddressBean newAddressBean);

    @PutMapping("/api/addresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    AddressBean updateAddress(@PathVariable("id") Integer id, @RequestBody AddressBean addressBean);

    @DeleteMapping("/api/addresses/{id}")
    void deleteAddress(@PathVariable("id") Integer id);

}
