package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService
{
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(UUID beerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
