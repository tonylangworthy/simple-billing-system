package com.langworthytech.simplebillingsystem.repository;

import com.langworthytech.simplebillingsystem.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

    Page<Invoice> findAll(Pageable pageable);

    Optional<Invoice> findByInvoiceNum(String invoiceNum);
}
