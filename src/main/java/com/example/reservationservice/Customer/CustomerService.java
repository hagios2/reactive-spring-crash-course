package com.example.reservationservice.Customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    protected final CustomerRepository customerRepository;

    public List<Customer>loadAllCustomers(){
        long start = System.currentTimeMillis();
        List<Customer> customers = customerRepository.getCustomer();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end + start));
        return customers;
    }

    public Flux<Customer> loadAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerRepository.getCustomerStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start));
        return customers;
    }
}
