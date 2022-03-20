package com.example.reservationservice.Customer.Handlers;

import com.example.reservationservice.Customer.Customer;
import com.example.reservationservice.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

    @Autowired
    private CustomerRepository customerRepository;

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        Flux<Customer> customerList = customerRepository.getCustomerStream();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerList, Customer.class);
    }
}
