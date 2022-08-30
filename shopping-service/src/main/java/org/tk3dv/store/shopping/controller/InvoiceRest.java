package org.tk3dv.store.shopping.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.tk3dv.store.shopping.model.Customer;
import org.tk3dv.store.shopping.model.Region;
import org.tk3dv.store.shopping.repository.entity.Invoice;
import org.tk3dv.store.shopping.service.InvoiceService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceRest {

    @Autowired
    InvoiceService service;

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices(){
        List<Invoice> invoices = service.findInvoiceAll();
        if(invoices.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(invoices);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetAll")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice>getInvoice(@PathVariable("id") Long id){
        log.info("Fetching Invoice with id {}", id);
        Invoice invoice = service.getInvoice(id);
        if(invoice == null){
            log.error("Invoice with id {} not found", id);
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }
    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackSaveAll")
    @PostMapping
    public ResponseEntity<Invoice>createInvoice(@Valid @RequestBody Invoice invoice,
                                                BindingResult result)
    {
        log.info("Creating Invoice : {}", invoice);
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Invoice invoiceDB = service.createInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice>updateInvoice(@PathVariable("id") Long id
            ,@RequestBody Invoice invoice ){
        log.info("Updating Invoice with id {}", id);
        invoice.setId(id);
        Invoice currentInvoice = service.updateInvoice(invoice);

        if(currentInvoice == null){
            log.error("Unable to update. Invoice with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentInvoice);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") Long id){
        log.info("Fetching & Deleting Invoice with id {}",id);

        Invoice invoice = service.getInvoice(id);
        if(invoice == null){
            return ResponseEntity.notFound().build();
        }
        invoice = service.deleteInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

    private ResponseEntity<Invoice>fallbackGetAll(@PathVariable("id") Long id, RuntimeException exception){
        return new ResponseEntity("No tiene usuario, ni productos",HttpStatus.OK);
    }

    private ResponseEntity<Invoice>fallbackSaveAll(@Valid @RequestBody Invoice invoice, BindingResult result, RuntimeException exception){
        Customer customerFake = new Customer();
        customerFake.setId(0L);
        customerFake.setFirstName("John");
        customerFake.setLastName("Doe");
        customerFake.setEmail("1@a.c");
        customerFake.setState("Far far away");
        customerFake.setNumberId("555");
        customerFake.setPhotoUrl("fake");
        Region regionFake = new Region();
        regionFake.setName("Fake");
        regionFake.setId(0L);
        customerFake.setRegion(regionFake);
        invoice.setCustomer(customerFake);

        return ResponseEntity.ok(invoice);
    }










    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


}
