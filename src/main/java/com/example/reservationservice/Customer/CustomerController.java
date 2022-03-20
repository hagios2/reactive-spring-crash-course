package com.example.reservationservice.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer>getAllCustomers() {
        return customerService.loadAllCustomers();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/stream")
    public Flux<Customer> getAllCustomerStream() {
        return customerService.loadAllCustomerStream();
    }
}
