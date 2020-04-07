package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    Iterable<Product> findAllByAccount(Account account);
}
