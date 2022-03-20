package com.example.reservationservice.Reservation;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReservationRepository  extends ReactiveCrudRepository<Reservation, String> {
}
