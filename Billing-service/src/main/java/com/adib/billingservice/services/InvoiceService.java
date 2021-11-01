package com.adib.billingservice.services;

import com.adib.billingservice.dto.InvoiceRequestDTO;
import com.adib.billingservice.dto.InvoiceResponseDTO;
import com.adib.billingservice.exception.CustomerNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException;
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoicesByCustomerId(String customerId);
    List<InvoiceResponseDTO> getAllInvoice();
}
