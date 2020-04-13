package com.langworthytech.simplebillingsystem.customer;

import com.langworthytech.simplebillingsystem.customer.dto.CustomerFormRequest;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> findAllCustomers();

    List<Customer> searchByEmailStartsWith(String term);

    Customer createCustomer(Customer customer);

    Customer findOrCreateCustomer(CustomerFormRequest customerModel);

    Customer editCustomerInfo(Customer customer);

    Optional<Customer> findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    Optional<Customer> findCustomerByPhone(String phone);

    Optional<Customer> findCustomerByEmailAndPhone(String email, String phone);
}
