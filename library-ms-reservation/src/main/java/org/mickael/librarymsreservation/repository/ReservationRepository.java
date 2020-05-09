package org.mickael.librarymsreservation.repository;

import org.mickael.librarymsreservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByCustomerId(Integer customerId);

    List<Reservation> findAllByBookId(Integer bookId);

    Optional<Reservation> findByCustomerId(Integer customerId);
}
