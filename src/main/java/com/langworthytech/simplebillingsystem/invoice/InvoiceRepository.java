package com.langworthytech.simplebillingsystem.invoice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Optional<Invoice> findByInvoiceNum(String invoiceNum);
}
