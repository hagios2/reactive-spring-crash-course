package com.example.reservationservice.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    protected CustomerRepository customerRepository;

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
