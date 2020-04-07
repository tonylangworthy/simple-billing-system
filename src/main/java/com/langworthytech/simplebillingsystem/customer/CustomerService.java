package com.langworthytech.simplebillingsystem.customer;

import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public Customer createCustomer(Customer customer) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        customer.setAccount(customUserDetails.getAccount());
        return customerRepository.save(customer);
    }

    @Override
    public Customer findOrCreateCustomer(CustomerFormModel customerModel) {
        Optional<Customer> optionalCustomer = findCustomerByEmailAndPhone(customerModel.getEmail(), customerModel.getPhone());
        return optionalCustomer.orElseGet(() -> {
            Customer customer = new Customer();
            customer.setCompanyName(customerModel.getCompanyName());
            customer.setFirstName(customerModel.getFirstName());
            customer.setLastName(customerModel.getLastName());
            customer.setEmail(customerModel.getEmail());
            customer.setPhone(customerModel.getPhone());
            return createCustomer(customer);
        });


    }

    @Override
    public Customer editCustomerInfo(Customer customer) {
        return null;
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    @Override
    public Optional<Customer> findCustomerByEmailAndPhone(String email, String phone) {
        return customerRepository.findByEmailAndPhone(email, phone);
    }
}
