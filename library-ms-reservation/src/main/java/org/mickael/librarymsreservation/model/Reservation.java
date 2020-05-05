package org.mickael.librarymsreservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.reservation_reservation_id_seq")
    @SequenceGenerator(name = "public.reservation_reservation_id_seq", sequenceName = "public.reservation_reservation_id_seq", allocationSize = 1)
    @Column(name = "reservation_id")
    private Integer id;








}
