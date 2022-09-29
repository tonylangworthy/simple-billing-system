package com.langworthytech.simplebillingsystem.repository;

import com.langworthytech.simplebillingsystem.model.Account;
import com.langworthytech.simplebillingsystem.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    @Query("select p from Product p join p.user u join u.account a where a = :account")
    Iterable<Product> findAllByAccount(@Param("account") Account account);

    List<Product> findByNameContaining(String searchTerm);
}
