package org.tk3dv.store.shopping.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tk3dv.store.shopping.client.CustomerClient;
import org.tk3dv.store.shopping.client.ProductClient;
import org.tk3dv.store.shopping.model.Customer;
import org.tk3dv.store.shopping.model.Product;
import org.tk3dv.store.shopping.repository.InvoiceItemsRepository;
import org.tk3dv.store.shopping.repository.InvoiceRepository;
import org.tk3dv.store.shopping.repository.entity.Invoice;
import org.tk3dv.store.shopping.repository.entity.InvoiceItem;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository repository;

    @Autowired
    InvoiceItemsRepository itemsRepository;

// ---------------------------------------------

    @Autowired
    ProductClient productClient;
    @Autowired
    CustomerClient customerClient;


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
        invoiceDB = repository.save(invoice);
        invoiceDB.getItems().forEach( items -> {
            productClient.updateStockProduct(
                    items.getProductId(),
                    items.getQuantity()* -1
            );
        });
        return invoiceDB;
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

        Invoice invoice = repository.findById(id).orElse(null);
        if(invoice != null){
            Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
            invoice.setCustomer(customer);
            List<InvoiceItem> listaItems = invoice.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoice.setItems(listaItems);
        }
        return invoice;
    }






























}
