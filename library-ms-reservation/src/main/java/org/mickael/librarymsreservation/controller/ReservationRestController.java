package org.mickael.librarymsreservation.controller;

import org.mickael.librarymsreservation.model.Reservation;
import org.mickael.librarymsreservation.service.contract.ReservationServiceContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationRestController.class);
    private final ReservationServiceContract reservationServiceContract;

    @Autowired
    public ReservationRestController(ReservationServiceContract reservationServiceContract) {
        this.reservationServiceContract = reservationServiceContract;
    }

    @GetMapping
    public List<Reservation> getReservations(){
        List<Reservation> reservations = reservationServiceContract.findAll();
        return reservations;
    }

    @GetMapping("/customer/{customerId}")
    public List<Reservation> getCustomerReservations(@PathVariable Integer customerId){
        List<Reservation> reservations = reservationServiceContract.findAllByCustomerId(customerId);
        return reservations;
    }

    @PostMapping
    public Reservation createReservation(Reservation reservation){
        Integer position = reservationServiceContract.findReservationPositionForBookId(reservation.getBookId());
        Reservation reservationToSave = new Reservation();

        reservationToSave.setBookAvailable(false);
        reservationToSave.setBookId(reservation.getBookId());
        reservationToSave.setPosition(position);
    }
}
