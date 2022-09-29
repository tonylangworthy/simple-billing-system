package com.langworthytech.simplebillingsystem.repository;

import com.langworthytech.simplebillingsystem.model.InvoiceStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceStatusRepository extends CrudRepository<InvoiceStatus, Long> {

    Optional<InvoiceStatus> findByName(String name);
}
