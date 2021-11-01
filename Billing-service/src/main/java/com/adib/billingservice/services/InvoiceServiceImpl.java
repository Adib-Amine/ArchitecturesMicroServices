package com.adib.billingservice.services;

import com.adib.billingservice.dto.InvoiceRequestDTO;
import com.adib.billingservice.dto.InvoiceResponseDTO;
import com.adib.billingservice.exception.CustomerNotFoundException;
import com.adib.billingservice.feign.CustomerRestClient;
import com.adib.billingservice.mappers.InvoiceMapper;
import com.adib.billingservice.models.Customer;
import com.adib.billingservice.models.Invoice;
import com.adib.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException {
        Invoice invoice = invoiceMapper.fromInvoiceResponseDTO(invoiceRequestDTO);
        Customer customer = null;
        try {
            customer = customerRestClient.customerById(invoiceRequestDTO.getCustomerId());
        }catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found");
        }
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice save = invoiceRepository.save(invoice);
        save.setCustomer(customer);
        return invoiceMapper.invoiceToInvoiceResponseDTO(save);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        Customer customer = customerRestClient.customerById(invoice.getCustomerID());
        invoice.setCustomer(customer);
        return invoiceMapper.invoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerID(customerId);
        for (Invoice invoice :invoices) {
            Customer customer = customerRestClient.customerById(invoice.getCustomerID());
            invoice.setCustomer(customer);
        }
        return invoices.stream()
                .map(invoice->invoiceMapper.invoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoice() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice :invoices) {
            Customer customer = customerRestClient.customerById(invoice.getCustomerID());
            invoice.setCustomer(customer);
        }
        return  invoices.stream()
                .map(invoice->invoiceMapper.invoiceToInvoiceResponseDTO(invoice))
                .collect(Collectors.toList());
    }
}
