package com.example.reservationservice.Customer.RouterConfig;

import com.example.reservationservice.Customer.Handlers.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Bean
    public RouterFunction<ServerResponse> router(){
        return RouterFunctions
                .route()
                .GET("/api/customers", customerHandler::getCustomers)
                .GET("/api/get/{customerId}/customer", customerHandler::getCustomer)
                .POST("/api/create/customer", customerHandler::createCustomer)
                .build();
    }
}
