package com.langworthytech.simplebillingsystem.customer;

import java.util.Optional;

public interface ICustomerService {

    Customer createCustomer(Customer customer);

    Customer findOrCreateCustomer(CustomerFormModel customerModel);

    Customer editCustomerInfo(Customer customer);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerByPhone(String phone);

    Optional<Customer> findCustomerByEmailAndPhone(String email, String phone);
}
