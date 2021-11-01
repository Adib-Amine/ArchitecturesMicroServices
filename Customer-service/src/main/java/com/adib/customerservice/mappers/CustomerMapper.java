package com.adib.customerservice.mappers;

import com.adib.customerservice.dto.CustomerRequestDTO;
import com.adib.customerservice.dto.CustomerResponseDTO;
import com.adib.customerservice.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOCustom(CustomerRequestDTO customerRequestDTO);
}
