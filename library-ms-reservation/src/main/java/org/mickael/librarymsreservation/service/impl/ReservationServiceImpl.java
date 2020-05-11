package org.mickael.librarymsreservation.service.impl;

import org.mickael.librarymsreservation.exception.ReservationAlreadyExistException;
import org.mickael.librarymsreservation.exception.ReservationNotFoundException;
import org.mickael.librarymsreservation.model.Reservation;
import org.mickael.librarymsreservation.repository.ReservationRepository;
import org.mickael.librarymsreservation.service.contract.ReservationServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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
    public Reservation save(Reservation reservation, List<LocalDate> listReturnLoanDate) {
        Reservation reservationToSave = new Reservation();

        //check if the customer already had a reservation
        Reservation reservationInBdd = reservationRepository.findByCustomerIdAndBookId(reservation.getCustomerId(), reservation.getBookId());
        if (!reservationInBdd.equals(null)){
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
        reservationToSave.setSoonDisponibilityDate(listReturnLoanDate.get(lastPosition));

        //reservationToSave.setBookAvailable(reservation.isBookAvailable());
        //reservationToSave.setCustomerPriority(reservation.isCustomerPriority());

        return reservationRepository.save(reservationToSave);
    }



    @Override
    public void updateResaBookId(Integer bookId, Integer numberOfCopies) {
        //get list resa for this book
        List<Reservation> reservations = reservationRepository.findAllByBookId(bookId);

        //send mail to reservation customer
        for (int i = 0; i < numberOfCopies; i++) {
            //send mail
        }
        //update list resa ?


    }

    @Override
    public void updateDateResaBookId(Integer bookId, List<LocalDate> listReturnLoanDate) {
        List<Reservation> reservations = reservationRepository.findAllByBookId(bookId);
        reservations.sort(Comparator.comparing(Reservation::getPosition));
        //change soon to return date
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).setSoonDisponibilityDate(listReturnLoanDate.get(i));
        }
    }

    @Override
    public void delete(Integer id, List<LocalDate> listReturnLoanDate) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (!optionalReservation.isPresent()){
            throw new ReservationNotFoundException("Reservation not Found");
        }
        //get resa
        Reservation reservationToDelete = optionalReservation.get();

        //delete
        reservationRepository.deleteById(id);

        //modify list resa
        //get new list of all reservations for the book
        List<Reservation> reservations = reservationRepository.findAllByBookId(reservationToDelete.getBookId());

        //set last position in the reservation list
        //Integer lastPosition = reservations.size();
        Integer deleteReservationPosition = reservationToDelete.getPosition();

        //change all position
        for (Reservation reservation : reservations){
            if (reservation.getPosition() > deleteReservationPosition){
                reservation.setPosition(reservation.getPosition() - 1);
            }
        }
        reservations.sort(Comparator.comparing(Reservation::getPosition));
        //change soon to return date
        for (int i = 0; i < reservations.size(); i++) {
            reservations.get(i).setSoonDisponibilityDate(listReturnLoanDate.get(i));
        }
        reservationRepository.saveAll(reservations);

    }

    @Override
    public List<Reservation> findAllByCustomerId(Integer customerId) {
        return null;
    }


    @Override
    public boolean checkIfReservationExistForCustomerIdAndBookId(Integer customerId, Integer bookId) {
        return reservationRepository.existByCustomerIdAndBookId(customerId, bookId);
    }

    @Override
    public Reservation findByCustomerIdAndBookId(Integer customerId, Integer bookId) {
        Reservation reservation = reservationRepository.findByCustomerIdAndBookId(customerId, bookId);
        if (reservation.equals(null)){
            throw new ReservationNotFoundException("No reservation for this customer and book");
        }
        return reservation;
    }
}
