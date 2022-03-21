package com.example.reservationservice.Customer;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/")
    public List<Customer>getAllCustomers() {
        return customerService.loadAllCustomers();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/stream")
    public Flux<Customer> getAllCustomerStream() {
        return customerService.loadAllCustomerStream();
    }
}
