package com.example.reservationservice;

import com.example.reservationservice.Reservation.Reservation;
import com.example.reservationservice.Reservation.ReservationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> route(ReservationRepository repository) {
		return RouterFunctions
				.route()
				.GET("/reservation", serverRequest -> ServerResponse.ok().body(repository.findAll(), Reservation.class))
				.build();
	}
}
