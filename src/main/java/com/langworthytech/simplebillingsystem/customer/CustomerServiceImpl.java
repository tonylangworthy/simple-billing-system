package com.langworthytech.simplebillingsystem.customer;

import com.langworthytech.simplebillingsystem.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Customer createCustomer(CustomerFormModel customerModel) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        logger.info(customUserDetails.getEmail());
        return new Customer();
    }

    @Override
    public Customer editCustomerInfo(Customer customer) {
        return null;
    }
}
