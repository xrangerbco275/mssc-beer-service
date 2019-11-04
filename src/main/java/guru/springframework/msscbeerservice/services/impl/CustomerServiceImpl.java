package guru.springframework.msscbeerservice.services.impl;

import guru.springframework.msscbeerservice.services.CustomerService;
import guru.springframework.msscbeerservice.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService
{

    @Override
    public CustomerDto getCustomerById(UUID beerId)
    {
        log.debug("CustomerServiceImpl.getCustomerById");
        return new CustomerDto();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto beerDto)
    {
        log.debug("CustomerServiceImpl.saveNewCustomer");
        return new CustomerDto();
    }

    @Override
    public CustomerDto updateCustomer(UUID beerId, CustomerDto beerDto)
    {
        System.out.println("CustomerServiceImpl.updateCustomer");
        log.debug("CustomerServiceImpl.updateCustomer");
        return new CustomerDto();
    }

    @Override
    public void deleteCustomer(UUID beerId)
    {
        System.out.println("CustomerServiceImpl.deleteCustomer");
    }
}
