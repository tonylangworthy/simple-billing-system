package com.langworthytech.simplebillingsystem.invoice;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceStatusRepository extends CrudRepository<InvoiceStatus, Long> {

    Optional<InvoiceStatus> findByName(String name);
}
