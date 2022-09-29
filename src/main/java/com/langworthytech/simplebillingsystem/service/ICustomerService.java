package com.langworthytech.simplebillingsystem.service;

import com.langworthytech.simplebillingsystem.dto.request.CustomerFormRequest;
import com.langworthytech.simplebillingsystem.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Iterable<Customer> findAllCustomers();

    List<Customer> searchByEmailStartsWith(String term);

    Customer createCustomer(Customer customer);

    Customer findOrCreateCustomer(CustomerFormRequest customerModel);

    Customer editCustomerInfo(Customer customer);

    Optional<Customer> findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    Optional<Customer> findCustomerByPhone(String phone);

    Optional<Customer> findCustomerByEmailAndPhone(String email, String phone);
}
