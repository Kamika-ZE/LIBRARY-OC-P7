package org.mickael.librarymsreservation.service.contract;

import org.mickael.librarymsreservation.model.Reservation;

import java.util.List;

public interface ReservationServiceContract {

    List<Reservation> findAll();
    Reservation findById(Integer id);
    Reservation save(Reservation reservation);
    Reservation update(Reservation reservation);
    void delete(Reservation reservation);


    List<Reservation> findAllByCustomerId(Integer customerId);

    Integer findReservationPositionForBookId(Integer bookId);


}
