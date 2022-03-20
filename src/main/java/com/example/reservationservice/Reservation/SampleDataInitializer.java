package com.example.reservationservice.Reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Log4j2
public class SampleDataInitializer {

    private final ReservationRepository reservationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
       Flux<Reservation> saved = Flux
               .just("Josh", "Emma", "Sea", "Sam", "Prince", "Peniel", "Sebastien")
               .map(name -> new Reservation(null, name))
               .flatMap(reservationRepository::save);

       reservationRepository.deleteAll()
               .thenMany(saved)
               .thenMany(reservationRepository.findAll())
               .subscribe(log::info);
    }
}
