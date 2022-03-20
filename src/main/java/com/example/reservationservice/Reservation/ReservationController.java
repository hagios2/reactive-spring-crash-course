package com.example.reservationservice.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingsRequest {
    private String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingsResponse {
    private String greeting;
}

@Component
class  IntervalMessageProducer {
    Flux <GreetingsResponse> produceGreetings(GreetingsRequest name) {
       return Flux
        .fromStream(Stream.generate(() -> "Hello " + name.getName() + " @ " + Instant.now()))
       .map(GreetingsResponse::new)
       .delayElements(Duration.ofSeconds(1));
    }
}
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final IntervalMessageProducer intervalMessageProducer;

    @GetMapping("/reservations")
    Publisher <Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/sse/{n}")
    Publisher<GreetingsResponse> sse(@PathVariable String n) {
        return intervalMessageProducer.produceGreetings(new GreetingsRequest(n));
    }
}
