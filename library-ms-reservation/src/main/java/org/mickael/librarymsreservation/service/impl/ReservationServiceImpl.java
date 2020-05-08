package org.mickael.librarymsreservation.service.impl;

import org.mickael.librarymsreservation.model.Reservation;
import org.mickael.librarymsreservation.service.contract.ReservationServiceContract;

import java.util.List;

public class ReservationServiceImpl implements ReservationServiceContract {


    @Override
    public List<Reservation> findAll() {
        return null;
    }

    @Override
    public Reservation findById(Integer id) {
        return null;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation update(Reservation reservation) {
        return null;
    }

    @Override
    public void delete(Reservation reservation) {

    }

    @Override
    public List<Reservation> findAllByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public Integer findReservationPositionForBookId(Integer bookId) {
        return null;
    }
}
