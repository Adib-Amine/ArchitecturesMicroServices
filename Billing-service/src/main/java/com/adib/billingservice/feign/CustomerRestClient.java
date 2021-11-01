package com.adib.billingservice.feign;

import com.adib.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/api/cutomers/{id}")
    public Customer customerById(@PathVariable String id);

    @GetMapping(path = "/api/customers")
    public List<Customer> customers();
}
