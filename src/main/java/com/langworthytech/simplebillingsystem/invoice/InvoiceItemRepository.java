package com.langworthytech.simplebillingsystem.invoice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}
