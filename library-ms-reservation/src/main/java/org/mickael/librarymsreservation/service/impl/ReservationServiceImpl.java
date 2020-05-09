package org.mickael.librarymsreservation.service.impl;

import org.mickael.librarymsreservation.exception.ReservationAlreadyExistException;
import org.mickael.librarymsreservation.exception.ReservationNotFoundException;
import org.mickael.librarymsreservation.model.Reservation;
import org.mickael.librarymsreservation.repository.ReservationRepository;
import org.mickael.librarymsreservation.service.contract.ReservationServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationServiceContract {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


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
        Reservation reservationToSave = new Reservation();

        //check if the customer already had a reservation
        Optional<Reservation> optionalReservation = reservationRepository.findByCustomerId(reservation.getCustomerId());
        if (optionalReservation.isPresent()){
            throw new ReservationAlreadyExistException("Vous avez déjà une réservation pour ce livre.");
        }

        //get all the reservation for the book to know the position in the list
        List<Reservation> reservations = reservationRepository.findAllByBookId(reservation.getBookId());

        //set last position in the reservation list
        Integer lastPosition;
        if (reservations.isEmpty() || reservations.equals(null)){
            lastPosition = 0;
        } else {
            lastPosition = reservations.size();
        }

        reservationToSave.setCreationReservationDate(LocalDateTime.now());
        reservationToSave.setBookId(reservation.getBookId());
        reservationToSave.setCustomerId(reservation.getCustomerId());
        reservationToSave.setPosition(lastPosition + 1);

        reservationToSave.setSoonDisponibilityDate(reservation.getSoonDisponibilityDate());

        //reservationToSave.setBookAvailable(reservation.isBookAvailable());
        reservationToSave.setCustomerPriority(reservation.isCustomerPriority());

        return reservationRepository.save(reservationToSave);
    }

    @Override
    public Reservation update(Reservation reservation) {

        Optional<Reservation> reservationToUpdate = reservationRepository.findById(reservation.getId());
        if (!reservationToUpdate.isPresent()){
            throw new ReservationNotFoundException("Reservation not Found");
        }


        //get all reservations
        List<Reservation> allReservations = reservationRepository.findAll();

        //for each book check if a copy is dispo

        //
        return null;
    }

    @Override
    public void delete(Integer id) {
        //get resa
        Reservation reservationToDelete = reservationRepository.findById(id).get();

        //delete
        reservationRepository.deleteById(id);

        //modify list resa
        //get all the reservation for the book to know the position in the list
        List<Reservation> reservations = reservationRepository.findAllByBookId(reservationToDelete.getBookId());

        //set last position in the reservation list
        Integer lastPosition = reservations.size();

        Integer deletePosition = reservationToDelete.getPosition();

        //change all position
        for (Reservation reservation : reservations){
            if (reservation.getPosition() > deletePosition){
                reservation.setPosition(reservation.getPosition() - 1);
            }
        }
        reservationRepository.saveAll(reservations);

    }

    @Override
    public List<Reservation> findAllByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public Integer findReservationPositionForBookId(Integer bookId) {
        List<Reservation> reservations = reservationRepository.findAllByBookId(bookId);

        //sort the list by increase date
        Collections.sort(reservations, (a, b) -> a.getCreationReservationDate().compareTo(b.getCreationReservationDate()));

        //

        return null;
    }
}
