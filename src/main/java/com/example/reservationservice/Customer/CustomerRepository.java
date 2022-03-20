package com.example.reservationservice.Customer;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CustomerRepository {
    public List<Customer>getCustomer() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerRepository::sleepExecution)
                .peek(i->System.out.println("processing count : " + i))
                .mapToObj(i -> new Customer(i, "customer"+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomerStream() {
        return Flux.range(1, 50)
                .doOnNext(i->System.out.println("processing count : " + i))
                .map(i -> new Customer(i, "customer"+i));
    }

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
