package com.example.reservationservice.Customer.Handlers;

import com.example.reservationservice.Customer.Customer;
import com.example.reservationservice.Customer.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomerHandler {

    private final CustomerRepository customerRepository;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customerList = customerRepository.getCustomerStream();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerList, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest request) {
        int customerId = Integer.parseInt(request.pathVariable("customerId"));
        Mono<Customer> customer = customerRepository.getCustomerStream().filter(c -> c.getId()  == customerId).next();
        return ServerResponse.ok().body(customer, Customer.class);
    }

    public Mono<ServerResponse> createCustomer(ServerRequest request) {
        Mono<Customer> customer = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customer.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
