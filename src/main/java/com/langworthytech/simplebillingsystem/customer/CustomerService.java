package com.langworthytech.simplebillingsystem.customer;

public interface CustomerService {

    Customer createCustomer(CustomerFormModel customerModel);

    Customer editCustomerInfo(Customer customer);


}
