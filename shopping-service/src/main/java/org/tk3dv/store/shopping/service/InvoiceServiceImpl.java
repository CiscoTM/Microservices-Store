package org.tk3dv.store.shopping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tk3dv.store.shopping.repository.InvoiceItemsRepository;
import org.tk3dv.store.shopping.repository.InvoiceRepository;
import org.tk3dv.store.shopping.repository.entity.Invoice;

import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository repository;

    @Autowired
    InvoiceItemsRepository itemsRepository;


    @Override
    public List<Invoice> findInvoiceAll() {
        return repository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = repository.findByNumberInvoice(invoice.getNumberInvoice());
        if(invoiceDB != null){
            return invoiceDB;
        }
        invoice.setState("CREATED");
        return repository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
       Invoice invoiceDB = getInvoice(invoice.getId());
       if(invoiceDB == null){
           return null;
       }

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());


       return repository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if(invoiceDB == null){
            return null;
        }
        invoiceDB.setState("DELETED");
        return repository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return repository.findById(id).orElse(null);
    }
}
