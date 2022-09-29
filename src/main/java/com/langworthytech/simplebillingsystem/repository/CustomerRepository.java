package com.langworthytech.simplebillingsystem.repository;

import com.langworthytech.simplebillingsystem.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByEmailAndPhone(String email, String phone);

    List<Customer> findByEmailStartsWith(String searchTerm);
}
