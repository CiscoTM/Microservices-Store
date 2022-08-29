package org.tk3dv.store.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tk3dv.store.shopping.repository.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {
}
