package com.adib.customerservice;

import com.adib.customerservice.dto.CustomerRequestDTO;
import com.adib.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerService customerService) {
        return args -> {
          customerService.save(new CustomerRequestDTO("A1","Amine","amine@gmail.com"));
          customerService.save(new CustomerRequestDTO("A2","Adib","adib@gmail.com"));
        };
    }

}
