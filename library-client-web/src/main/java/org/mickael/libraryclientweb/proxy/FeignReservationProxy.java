package org.mickael.libraryclientweb.proxy;

import org.mickael.libraryclientweb.bean.reservation.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "library-ms-reservation", url = "localhost:8300")
public interface FeignReservationProxy {

    @PostMapping("/api/reservations")
    Reservation createReservation(Reservation reservation);

    @GetMapping("/api/reservations/customer/{customerId}")
    List<Reservation> getCustomerReservations(@PathVariable("customerId") Integer customerId);

    @DeleteMapping("/api/reservations/customer/{customerId}/book/{bookId}/")
    void deleteReservationAfterLoan(@PathVariable("customerId") Integer customerId, @PathVariable("bookId") Integer bookId);

}
