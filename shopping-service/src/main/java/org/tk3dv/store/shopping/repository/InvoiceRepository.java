package org.tk3dv.store.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tk3dv.store.shopping.repository.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice>findByCustomerId(Long id);
    public Invoice findByNumberInvoice(String numberInvoice);
}
