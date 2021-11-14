package com.adib.billingservice.web;

import com.adib.billingservice.dto.InvoiceRequestDTO;
import com.adib.billingservice.dto.InvoiceResponseDTO;
import com.adib.billingservice.exception.CustomerNotFoundException;
import com.adib.billingservice.services.InvoiceService;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InvoiceRestController {
    private InvoiceService invoiceService;

    public InvoiceRestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> getAllInvoice() {
        return invoiceService.getAllInvoice();
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable String id) {
        return invoiceService.getInvoice(id);
    }

    @GetMapping(path = "/invoiceByCutomer/{id}")
    public List<InvoiceResponseDTO> getInvoicesByCustomrt(@PathVariable String id) {
        return invoiceService.invoicesByCustomerId(id);
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException {
        return invoiceService.save(invoiceRequestDTO);
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        return e.getMessage();
    }

}
