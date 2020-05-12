package org.mickael.libraryclientweb.controller;

import org.mickael.libraryclientweb.proxy.FeignBookProxy;
import org.mickael.libraryclientweb.proxy.FeignCustomerProxy;
import org.mickael.libraryclientweb.proxy.FeignReservationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationController {

    private final FeignReservationProxy feignReservationProxy;
    private final FeignCustomerProxy feignCustomerProxy;
    private final FeignBookProxy feignBookProxy;

    @Autowired
    public ReservationController(FeignReservationProxy feignReservationProxy, FeignCustomerProxy feignCustomerProxy, FeignBookProxy feignBookProxy) {
        this.feignReservationProxy = feignReservationProxy;
        this.feignCustomerProxy = feignCustomerProxy;
        this.feignBookProxy = feignBookProxy;
    }

/*    @GetMapping("/reservations/book/{bookId}/reserve")
    public String showReservForm(@PathVariable Integer id, @ModelAttribute("reservation") Reservation newReservation, Model model){
        BookBean book = feignBookProxy.retrieveBook(id*//*, accessToken*//*);
        CustomerBean customerBean = feignCustomerProxy.

        model.addAttribute()
        return "reservation";
    }

    @PostMapping("/reservations/book/{bookId}/reserve")
    public String reserveBook(@PathVariable Integer id, @ModelAttribute("reservation") Reservation newReservation, Model model){


        feignReservationProxy.createReservation(newReservation);

        return BOOK;
    }*/
}
