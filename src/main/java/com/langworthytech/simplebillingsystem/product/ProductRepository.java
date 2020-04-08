package com.langworthytech.simplebillingsystem.product;

import com.langworthytech.simplebillingsystem.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    @Query("select p from Product p join p.user u join u.account a where a = :account")
    Iterable<Product> findAllByAccount(@Param("account") Account account);
}
