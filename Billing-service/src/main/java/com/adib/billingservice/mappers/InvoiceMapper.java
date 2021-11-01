package com.adib.billingservice.mappers;


import com.adib.billingservice.dto.InvoiceRequestDTO;
import com.adib.billingservice.dto.InvoiceResponseDTO;
import com.adib.billingservice.models.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceResponseDTO invoiceToInvoiceResponseDTO(Invoice invoice);
    Invoice fromInvoiceResponseDTO(InvoiceRequestDTO invoiceRequestDTO);
}
