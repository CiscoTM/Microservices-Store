package org.tk3dv.store.shopping.service;

import org.tk3dv.store.shopping.repository.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice>findInvoiceAll();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice(Long id);


}
