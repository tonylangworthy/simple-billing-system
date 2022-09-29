package com.langworthytech.simplebillingsystem.repository;

import com.langworthytech.simplebillingsystem.model.InvoiceItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends CrudRepository<InvoiceItem, Long> {
}
